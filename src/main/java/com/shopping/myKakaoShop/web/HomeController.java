package com.shopping.myKakaoShop.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    @GetMapping("")
    public String home() {
        return "index";
    }

    @GetMapping("/user/join")
    public String joinForm() {
        log.debug("join form in.");
        return "/user/joinForm";
    }

    @GetMapping("/user/login")
    public String loginForm() {
        log.debug("login form in.");
        return "/user/loginForm";
    }
}
