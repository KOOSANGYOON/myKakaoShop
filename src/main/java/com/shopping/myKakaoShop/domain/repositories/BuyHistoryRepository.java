package com.shopping.myKakaoShop.domain.repositories;

import com.shopping.myKakaoShop.domain.BuyHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyHistoryRepository extends JpaRepository<BuyHistory, Long> {
    Iterable<BuyHistory> findByCustomerId(Long id);
}
