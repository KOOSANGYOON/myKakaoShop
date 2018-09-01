package com.shopping.myKakaoShop.web;

import com.shopping.myKakaoShop.domain.ImageRepository;
import com.shopping.myKakaoShop.domain.ItemRepository;
import com.shopping.myKakaoShop.service.ItemService;
import com.shopping.myKakaoShop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("")
    public String home(Model model) {
        log.debug("hihi");
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

    @GetMapping("/shop")
    public String shopMain(Model model) {
        log.debug("shop main in.");
        model.addAttribute("items", itemRepository.findAll());
        return "/product";
    }

}
