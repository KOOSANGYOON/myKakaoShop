package com.shopping.myKakaoShop.dto;

import lombok.Getter;

@Getter
public class UserDto {
    private String userId;
    private String passwd;
    private String name;
    private String email;

    public UserDto() {}
    public UserDto(String userId, String passwd, String name, String email) {
        this.userId = userId;
        this.passwd = passwd;
        this.name = name;
        this.email = email;
    }
}
