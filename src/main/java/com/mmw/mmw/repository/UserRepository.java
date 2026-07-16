package com.mmw.mmw.repository;

import com.mmw.mmw.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    //Optional로 반환하여 Null 방지
    Optional<User> findByEmail(String email);

    //이메일 중복 여부
    boolean existsByEmail(String email);
}
