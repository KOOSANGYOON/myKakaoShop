package com.shopping.myKakaoShop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shopping.myKakaoShop.support.domain.AbstractEntity;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class User extends AbstractEntity {

    @Column(nullable = false, unique = true)
    private String userId;

    @Size(min = 3, max = 15)
    @Column(nullable = false)
    @JsonIgnore
    private String passwd;

    @Size(min = 3, max = 20)
    @Column(nullable = false)
    private String name;

    private String email;

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "user_between_product",
//            joinColumns = @JoinColumn(name = "product_id"),
//            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @OneToMany(fetch = FetchType.EAGER)
    private List<Product> boughtProducts = new ArrayList<Product>();

    private int mileage = 0;

    private boolean deleted = false;

    public User() {}
    public User(String userId, String passwd, String name, String email) {
        this.userId = userId;
        this.passwd = passwd;
        this.name = name;
        this.email = email;
    }

}
