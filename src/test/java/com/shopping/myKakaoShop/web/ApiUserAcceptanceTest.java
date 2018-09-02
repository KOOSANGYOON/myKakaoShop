package com.shopping.myKakaoShop.web;

import com.shopping.myKakaoShop.domain.User;
import com.shopping.myKakaoShop.domain.repositories.UserRepository;
import com.shopping.myKakaoShop.dto.UserDto;
import com.shopping.myKakaoShop.support.test.AcceptanceTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ApiUserAcceptanceTest extends AcceptanceTest {

    private static final Logger log = LoggerFactory.getLogger(ApiUserAcceptanceTest.class);

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void joinTest_success() {        //회원 가입 성공 테스트
        UserDto newDto = new UserDto("testId", "testPasswd", "testName", "testEmail@gmail.com");
        ResponseEntity<String> response = template.postForEntity("/api/users/join", newDto, String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertNotNull(userRepository.findByUserId("testId"));
    }

    @Test
    public void joinTest_fail_not_unique_id_or_not_valid_email() {      //이름과 이메일에 해당하는 필드의 유효성 체크를 확인한다.
        UserDto newDto = new UserDto("sameId", "testPasswd", "testName", "testEmail@gmail.com");
        UserDto sameNameDto = new UserDto("sameId", "testPasswd", "testName", "testEmail@gmail.com");
        UserDto notValidEmailDto = new UserDto("notSameId", "testPasswd", "testName", "testEmail");

        ResponseEntity<String> response = template.postForEntity("/api/users/join", newDto, String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));

        ResponseEntity<String> response2 = template.postForEntity("/api/users/join", sameNameDto, String.class);
        assertThat(response2.getStatusCode(), is(HttpStatus.INTERNAL_SERVER_ERROR));

        ResponseEntity<String> response3 = template.postForEntity("/api/users/join", notValidEmailDto, String.class);
        assertThat(response3.getStatusCode(), is(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @Test
    public void loginTest_success_check_encodePassword_also() {     //로그인 성공 및 비밀번호 encode 기능 잘 되는지 테스트
        UserDto newDto = new UserDto("loginId", "testPasswd", "testName", "loginEmail@gmail.com");
        ResponseEntity<String> response = template.postForEntity("/api/users/join", newDto, String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));

        User savedUser = userRepository.findByUserId("loginId").orElseThrow(() -> new NullPointerException());
        assertNotEquals(savedUser.getPasswd(), is("testPasswd"));       //그냥 넣으면 비밀번호 다름.
        assertTrue(passwordEncoder.matches("testPasswd", savedUser.getPasswd()));   //encoding 후 비교시에 같아야 한다.

        ResponseEntity<String> loginResponse = template.postForEntity("/api/users/login", newDto, String.class);
        assertThat(loginResponse.getStatusCode(), is(HttpStatus.OK));
    }

    @Test
    public void loginTest_fail_not_exist_user() {       //존재하지 않는 유저의 로그인 시,
        UserDto newDto = new UserDto("notExistUser", "notExistUserPw", "notExistUserName", "notExistUser@naver.com");
        ResponseEntity<String> loginResponse = template.postForEntity("/api/users/login", newDto, String.class);
        assertThat(loginResponse.getStatusCode(), is(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @Test
    public void loginTest_fail_not_correct_passwd() {       //비밀번호 틀렸을 시,
        UserDto newDto = new UserDto("userOne", "test", "userOne", "userOne@gmail.com");
        UserDto incorrectPasswdDto = new UserDto("userOne", "test1", "userOne", "userOne@gmail.com");

        ResponseEntity<String> response = template.postForEntity("/api/users/join", newDto, String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));

        ResponseEntity<String> loginResponse = template.postForEntity("/api/users/login", incorrectPasswdDto, String.class);
        assertThat(loginResponse.getStatusCode(), is(HttpStatus.INTERNAL_SERVER_ERROR));
    }
}
