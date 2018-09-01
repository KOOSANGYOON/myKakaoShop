package com.shopping.myKakaoShop.domain;

import com.shopping.myKakaoShop.dto.ItemDto;
import com.shopping.myKakaoShop.support.domain.AbstractEntity;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Item extends AbstractEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @Lob
    private String contents;

    private int cost;

    private String mainImagePath;

    @OneToMany(mappedBy = "item")
    private List<Image> images = new ArrayList<>();

    public Item() {}
    public Item(String name, String contents, int cost, String mainImagePath) {
        this.name = name;
        this.contents = contents;
        this.cost = cost;
        this.mainImagePath = mainImagePath;
    }

    public ItemDto toItemDto() {
        return new ItemDto(this.name, this.contents, this.cost, this.images);
    }
}
