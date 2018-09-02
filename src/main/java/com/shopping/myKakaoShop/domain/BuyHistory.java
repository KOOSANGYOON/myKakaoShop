package com.shopping.myKakaoShop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
public class BuyHistory {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "customer_id"))
    private User customer;

    @OneToOne(fetch = FetchType.EAGER)
    private Item item;

    private LocalDateTime buyTime = LocalDateTime.now();

    public BuyHistory() {}
    public BuyHistory(User customer, Item item) {
        this.customer = customer;
        this.item = item;
    }

    @Override
    public String toString() {
        return "BuyHistory{" +
                "id=" + id +
                ", customer=" + customer +
                ", item=" + item +
                ", buyTime=" + buyTime +
                '}';
    }

    @JsonIgnore
    public String getFormattedBuyTime() {
        return getFormattedDate(buyTime, "yyyy.MM.dd HH:mm:ss");
    }

    private String getFormattedDate(LocalDateTime dateTime, String format) {
        if (dateTime == null) {
            return "";
        }
        return dateTime.format(DateTimeFormatter.ofPattern(format));
    }
}
