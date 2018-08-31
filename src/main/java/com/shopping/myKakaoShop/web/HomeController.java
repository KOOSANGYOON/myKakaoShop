package com.shopping.myKakaoShop.web;

import com.shopping.myKakaoShop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private UserService userService;

    @GetMapping("")
    public String home() {
        return "index";
    }

    @GetMapping("/users/join")
    public String joinForm() {
        log.debug("join form in.");
        return "/user/joinForm";
    }

    @GetMapping("/users/login")
    public String loginForm() {
        log.debug("login form in.");
        return "/user/loginForm";
    }

}
