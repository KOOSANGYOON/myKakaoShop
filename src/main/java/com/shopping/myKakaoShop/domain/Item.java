package com.shopping.myKakaoShop.domain;

import com.shopping.myKakaoShop.support.domain.AbstractEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Item extends AbstractEntity {

    @Column(nullable = false, unique = true)
    private String name;

    private int cost;

//    private MultipartFile image;

    public Item() {}
    public Item(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }
}
