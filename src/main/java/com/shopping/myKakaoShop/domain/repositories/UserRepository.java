package com.shopping.myKakaoShop.domain.repositories;

import com.shopping.myKakaoShop.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserId(String userId);
}
