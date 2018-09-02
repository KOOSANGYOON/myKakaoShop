package com.shopping.myKakaoShop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shopping.myKakaoShop.dto.UserDto;
import com.shopping.myKakaoShop.support.domain.AbstractEntity;
import lombok.Getter;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class User extends AbstractEntity {

    public static final GuestUser GUEST_USER = new GuestUser();

    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false)
    @JsonIgnore
    private String passwd;

    @Size(min = 3, max = 20)
    @Column(nullable = false)
    private String name;

    @Email
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_between_product",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<Item> boughtItems = new ArrayList<Item>();

    private double mileage = 0.0;

    private boolean deleted = false;

    public User() {}
    public User(String userId, String passwd, String name, String email) {
        this.userId = userId;
        this.passwd = passwd;
        this.name = name;
        this.email = email;
    }

    @JsonIgnore
    public boolean isGuestUser() {
        return false;
    }

    public UserDto toUserDto() {
        return new UserDto(this.userId, this.passwd, this.name, this.email);
    }

    private static class GuestUser extends User {
        @Override
        public boolean isGuestUser() {
            return true;
        }
    }

    public static User from(UserDto userDto) {
        return new User(userDto.getUserId(), userDto.getPasswd(), userDto.getName(), userDto.getEmail());
    }

    public User setPasswd(String passwd) {
        this.passwd = passwd;
        return this;
    }

    //logic part
    public void buy(Item item) {
        this.boughtItems.add(item);
        double fivePercent = item.getCost() * 0.05;
        this.mileage += fivePercent;
    }
}
