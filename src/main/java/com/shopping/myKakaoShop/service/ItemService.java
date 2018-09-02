package com.shopping.myKakaoShop.service;

import com.shopping.myKakaoShop.domain.BuyHistory;
import com.shopping.myKakaoShop.domain.repositories.BuyHistoryRepository;
import com.shopping.myKakaoShop.domain.Item;
import com.shopping.myKakaoShop.domain.repositories.ItemRepository;
import com.shopping.myKakaoShop.domain.User;
import com.shopping.myKakaoShop.domain.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ItemService {

    private static final Logger log = LoggerFactory.getLogger(ItemService.class);

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private BuyHistoryRepository buyHistoryRepository;

    @Autowired
    private UserRepository userRepository;

    private BuyHistory makeBuyHistory(Long itemId, User loginUser) {
        Item targetItem = itemRepository.findOne(itemId);
        BuyHistory newBuyHistory = new BuyHistory(loginUser, targetItem);
        return buyHistoryRepository.save(newBuyHistory);
    }

    public BuyHistory buyItem(Long id, User loginUser) {
        BuyHistory newBuyHistory = makeBuyHistory(id, loginUser);
        log.debug("test1 : " + newBuyHistory + " | mileage : " + loginUser.getMileage());
        userRepository.save(loginUser.buy(newBuyHistory));
        log.debug("test2 : " + loginUser.getBuyHistories() + " | mileage : " + loginUser.getMileage());
        return newBuyHistory;
    }
}
