package com.yugwa.backend.global.token.repository;

import com.yugwa.backend.global.token.entity.AccessTokenBlackList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessTokenBlackListRepository extends JpaRepository<AccessTokenBlackList, Long> {
    boolean existsByAccessToken(String accessToken);
}
