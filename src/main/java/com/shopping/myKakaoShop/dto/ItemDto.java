package com.shopping.myKakaoShop.dto;

import com.shopping.myKakaoShop.domain.Image;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ItemDto {
    private String name;
    private String contents;
    private int cost;
    private List<Image> images = new ArrayList<>();

    public ItemDto() {}
    public ItemDto(String name, String contents, int cost, List<Image> images) {
        this.name = name;
        this.contents = contents;
        this.cost = cost;
        this.images = images;
    }
}
