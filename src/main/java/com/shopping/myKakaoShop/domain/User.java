package com.shopping.myKakaoShop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shopping.myKakaoShop.dto.UserDto;
import lombok.Getter;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
public class User {

    public static final GuestUser GUEST_USER = new GuestUser();

    @Id
    @GeneratedValue
    private Long id;

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

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderBy("id ASC")
    @JsonIgnore
    private List<BuyHistory> buyHistories = new ArrayList<>();

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
    public User buy(BuyHistory history) {
        this.buyHistories.add(history);
        double fivePercent = history.getItem().getCost() * 0.05;
        this.mileage += fivePercent;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", passwd='" + passwd + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", mileage=" + mileage +
                ", deleted=" + deleted +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Double.compare(user.mileage, mileage) == 0 &&
                deleted == user.deleted &&
                Objects.equals(id, user.id) &&
                Objects.equals(userId, user.userId) &&
                Objects.equals(passwd, user.passwd) &&
                Objects.equals(name, user.name) &&
                Objects.equals(email, user.email) &&
                Objects.equals(buyHistories, user.buyHistories);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userId, passwd, name, email, buyHistories, mileage, deleted);
    }
}
