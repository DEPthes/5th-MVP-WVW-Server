package com.mmw.mmw.service;

import com.mmw.mmw.domain.User;
import com.mmw.mmw.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User createUser(User user){
        if(userRepository.existsByEmail(user.getEmail())){
            throw new IllegalArgumentException("이미 가입된 이메일입니다.");

        }
        return userRepository.save(user);
    }

    @Transactional
    public User getUserByEmail(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 유저입니다."));
    }
}
