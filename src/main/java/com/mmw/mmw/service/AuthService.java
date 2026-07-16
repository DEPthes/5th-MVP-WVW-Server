package com.mmw.mmw.service;

import com.mmw.mmw.domain.User;
import com.mmw.mmw.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;

//    회뭔가입
    @Transactional
    public User signup(String email, String password, String name){
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);

        return userService.createUser(user);
    }

//    로그인
    @Transactional
    public User login(String email, String password){
        User user = userService.getUserByEmail(email);

        if (!user.getPassword().equals(password)){
            throw  new IllegalArgumentException("이메일과 비밀번호가 일치하지 않습니다.");
        }

        return user;
    }
}
