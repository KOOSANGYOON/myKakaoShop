package com.shopping.myKakaoShop.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Image {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String path = "/images/productImages/";

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    public Image() {}
    public Image(String name) {
        this.name = name;
        this.path += name;
    }
}
