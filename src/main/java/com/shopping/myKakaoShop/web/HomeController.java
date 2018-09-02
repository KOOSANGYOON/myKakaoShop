package com.shopping.myKakaoShop.web;

import com.shopping.myKakaoShop.domain.BuyHistory;
import com.shopping.myKakaoShop.domain.User;
import com.shopping.myKakaoShop.domain.repositories.BuyHistoryRepository;
import com.shopping.myKakaoShop.domain.repositories.ItemRepository;
import com.shopping.myKakaoShop.support.LoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class HomeController {

    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private BuyHistoryRepository buyHistoryRepository;

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

    @GetMapping("/items")
    public String shopMain(Model model) {
        log.debug("shop main in.");
        model.addAttribute("items", itemRepository.findAll());
        return "/product";
    }

    @GetMapping("/items/{id}")
    public String itemDetail(@PathVariable Long id, Model model) {
        log.debug("item detail in.");
        model.addAttribute("item", itemRepository.findOne(id));
        return "/product-detail";
    }

    @GetMapping("/users/mypage")
    public String myPage(Model model, @LoginUser User loginUser) {
        log.debug("my page controller in.");
        model.addAttribute("user", loginUser);
        List<BuyHistory> buyHistoryList = (List<BuyHistory>) buyHistoryRepository.findByCustomerId(loginUser.getId());
        model.addAttribute("histories", buyHistoryList);
        return "/user/mypage";
    }

}
