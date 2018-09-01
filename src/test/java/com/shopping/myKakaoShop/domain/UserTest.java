package com.shopping.myKakaoShop.domain;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class UserTest {

    private static final Logger log = LoggerFactory.getLogger(UserTest.class);

    @Test
    public void buyTest_success() {
        User newUser = new User("koo", "testpw", "구상윤", "testmail@gmail.com");
        assertEquals(newUser.getBoughtItems().size(), 0);

        Item newItem = new Item("ice-cream", "맛있는 아이스크림", 1000, "/testImagePath/test.jpg");
        newUser.buy(newItem);

        assertEquals(newUser.getBoughtItems().size(), 1);       //구매 내역에 잘 담겼는지,
        assertThat(newUser.getMileage(), is(50.0));             //마일리지가 제대로 쌓였는지 확인
    }
}
