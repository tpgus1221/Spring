package com.example.login.api;


import com.example.login.dto.SignUpForm;
import com.example.login.dto.SignUpReturn;
import com.example.login.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class SignUpApiController {
    @Autowired
    private SignUpService signUpService;


    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpForm signUpForm){
        SignUpReturn signUpReturn = signUpService.insertUser(signUpForm);

        if(signUpReturn.isIsnull()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("아이디를 입력해주세요.");
        }else if(signUpReturn.isIsdupl()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("이미 등록된 아이디입니다.");
        }else{
            return ResponseEntity.ok().body("회원가입 성공");
        }


    }
}
