package com.shopping.myKakaoShop.service;

import com.shopping.myKakaoShop.domain.ImageRepository;
import com.shopping.myKakaoShop.domain.ItemRepository;
import com.shopping.myKakaoShop.dto.ItemDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {

    private static final Logger log = LoggerFactory.getLogger(ItemService.class);

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ImageRepository imageRepository;

    public List<ItemDto> findAll() {
        return itemRepository.findAll().stream().map(item -> item.toItemDto()).collect(Collectors.toList());
    }
}
