package com.shopping.myKakaoShop.support.test;

import com.shopping.myKakaoShop.domain.User;
import com.shopping.myKakaoShop.domain.repositories.UserRepository;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public abstract class AcceptanceTest {

	private static final Logger log = LoggerFactory.getLogger(AcceptanceTest.class);

	private static final String DEFAULT_LOGIN_USER = "koo";
	private static final String ANOTHER_LOGIN_USER = "kakao";

	@Autowired
	private TestRestTemplate template;

	@Autowired
	private UserRepository userRepository;

	public TestRestTemplate template() {
		return template;
	} 

	public TestRestTemplate basicAuthTemplate() {
		return basicAuthTemplate(defaultUser());
	}

	public TestRestTemplate basicAuthTemplate(User loginUser) {
		log.debug("user Info : " + loginUser.getUserId() + loginUser.getPasswd());
		return template.withBasicAuth(loginUser.getUserId(), loginUser.getPasswd());
	}

	protected User defaultUser() {
		return findByUserId(DEFAULT_LOGIN_USER);
	}
	
	protected User anotherUser() {
		return findByUserId(ANOTHER_LOGIN_USER);
	}

	protected User findByUserId(String userId) {
		return userRepository.findByUserId(userId).get();
	}

	protected String createResource(String path, Object bodyPayload) {
		ResponseEntity<String> response = basicAuthTemplate().postForEntity(path, bodyPayload, String.class);
		assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
		return response.getHeaders().getLocation().getPath();
	}

	protected <T> T getResource(String location, Class<T> responseType, User loginUser) {
		return basicAuthTemplate(loginUser).getForObject(location, responseType);
	}
}
