package com.example.login.api;

import com.example.login.dto.LoginForm;
import com.example.login.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class LoginApiController {

    //REST 완
    private final LoginService loginService;

    public LoginApiController(LoginService loginService){
        this.loginService = loginService;
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginForm loginForm){

        boolean compare = loginService.compare(loginForm);

        if (compare) {
            return ResponseEntity.ok().body("로그인 성공");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("아이디 또는 비밀번호가 틀렸습니다.");
        }
    }
}
