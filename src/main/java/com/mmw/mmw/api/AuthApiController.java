package com.mmw.mmw.api;

import com.mmw.mmw.domain.User;
import com.mmw.mmw.service.AuthService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthApiController {

    private final AuthService authService;

//    회원가입
    @PostMapping("api/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest request){
        try{
            User registeredUser = authService.signup(
              request.getEmail(),
              request.getPassword(),
              request.getName()
            );
            return ResponseEntity.ok("회원가입 완료 ID : "+registeredUser.getId());
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

//    로그인
    @PostMapping("api/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request){
        try{
            User user = authService.login(request.getEmail(), request.getPassword());
            return ResponseEntity.ok("로그인 성공 ID : "+user.getName());
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Data
    public static class SignupRequest{
        private String email;
        private String password;
        private String name;
    }

    @Data
    public static class LoginRequest{
        private Long userId;
        private String email;
        private String password;
    }
}
