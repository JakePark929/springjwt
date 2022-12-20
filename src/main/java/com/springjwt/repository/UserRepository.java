package com.springjwt.repository;

import com.springjwt.domain.UserAccount;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserAccount, Long> {
    @EntityGraph(attributePaths = "authorities") // lazy 조회가 아닌 eager 조회로 authorities 정보를 같이 가져옴
    Optional<UserAccount> findOneWithAuthoritiesByUsername(String username);
}
