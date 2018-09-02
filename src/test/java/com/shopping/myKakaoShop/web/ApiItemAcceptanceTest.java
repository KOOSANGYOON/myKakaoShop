package com.shopping.myKakaoShop.web;

import com.shopping.myKakaoShop.domain.Item;
import com.shopping.myKakaoShop.domain.User;
import com.shopping.myKakaoShop.domain.repositories.BuyHistoryRepository;
import com.shopping.myKakaoShop.domain.repositories.ItemRepository;
import com.shopping.myKakaoShop.domain.repositories.UserRepository;
import com.shopping.myKakaoShop.dto.UserDto;
import com.shopping.myKakaoShop.support.test.AcceptanceTest;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;

public class ApiItemAcceptanceTest extends AcceptanceTest {

    private static final Logger log = LoggerFactory.getLogger(ApiItemAcceptanceTest.class);

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private BuyHistoryRepository buyHistoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private User joinUser(String userId) {        //회원 가입한 유저 반환
        UserDto newDto = new UserDto(userId, "testPasswd", "testName", "testEmail@gmail.com");
        return template.postForObject("/api/users/join", newDto, User.class);
    }

    @Test
    public void buyItemTest_success() {
        Item targetItem = itemRepository.findOne(1L);
        String url = "/api/items/" + targetItem.getId();
        ResponseEntity<String> failResponse = template.postForEntity(url, null, String.class);
        assertThat(failResponse.getStatusCode(), is(HttpStatus.INTERNAL_SERVER_ERROR));

        User user = userRepository.findByUserId("koo").orElseThrow(() -> new NullPointerException());
        ResponseEntity<String> successResponse = basicAuthTemplate(user).postForEntity(url, null, String.class);
        assertThat(successResponse.getStatusCode(), is(HttpStatus.OK));
    }
}
