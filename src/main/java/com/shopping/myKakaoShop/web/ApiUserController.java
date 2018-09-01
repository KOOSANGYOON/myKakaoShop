package com.shopping.myKakaoShop.web;

import com.shopping.myKakaoShop.domain.User;
import com.shopping.myKakaoShop.dto.UserDto;
import com.shopping.myKakaoShop.service.UserService;
import com.shopping.myKakaoShop.support.HttpSessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class ApiUserController {
    private static final Logger log = LoggerFactory.getLogger(ApiUserController.class);

    @Resource(name = "userService")
    private UserService userService;

    @PostMapping("/join")
    public User join(@Valid @RequestBody UserDto userDto) {
        log.debug("Join : {}", userDto);
        return userService.join(userDto);
    }

    @PostMapping("/login")
    public User login(@RequestBody UserDto userDto, HttpSession session) throws IllegalAccessException {
        try {
            User loginUser = userService.login(userDto);
            session.setAttribute(HttpSessionUtils.USER_SESSION_KEY, loginUser);
            return loginUser;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new IllegalAccessException();
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        session.removeAttribute(HttpSessionUtils.USER_SESSION_KEY);
        log.debug("log out !");
        return ResponseEntity.ok().build();
    }

}
