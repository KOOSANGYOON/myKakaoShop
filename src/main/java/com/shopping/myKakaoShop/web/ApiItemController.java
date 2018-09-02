package com.shopping.myKakaoShop.web;

import com.shopping.myKakaoShop.domain.BuyHistory;
import com.shopping.myKakaoShop.domain.User;
import com.shopping.myKakaoShop.service.ItemService;
import com.shopping.myKakaoShop.support.LoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/items")
public class ApiItemController {

    private static final Logger log = LoggerFactory.getLogger(ApiItemController.class);

    @Resource(name = "itemService")
    private ItemService itemService;

    @PostMapping("/{id}")
    public BuyHistory buyItem(@PathVariable Long id, @LoginUser User loginUser) {
        return itemService.buyItem(id, loginUser);
    }
}
