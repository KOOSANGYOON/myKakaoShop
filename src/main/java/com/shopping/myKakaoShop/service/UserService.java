package com.shopping.myKakaoShop.service;

import com.shopping.myKakaoShop.domain.User;
import com.shopping.myKakaoShop.domain.UserRepository;
import com.shopping.myKakaoShop.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Transactional
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private User makeUserFromDto(UserDto userDto) {
        User targetUser = User.from(userDto);
        return targetUser.setPasswd(passwordEncoder.encode(targetUser.getPasswd()));
    }

    public User join(UserDto userDto) {
        log.debug("user service join in.");
        User newUser = makeUserFromDto(userDto);
        return userRepository.save(newUser);
    }

    public User login(UserDto userDto) throws IllegalAccessException {
        User targetUser = userRepository.findByUserId(userDto.getUserId()).orElseThrow(() -> new NoSuchElementException());
        if (!passwordEncoder.matches(userDto.getPasswd(), targetUser.getPasswd())) {
            throw new IllegalAccessException();
        }
        return targetUser;
    }

}
