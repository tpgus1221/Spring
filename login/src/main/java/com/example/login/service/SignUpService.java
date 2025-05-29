package com.example.login.service;

import com.example.login.dto.SignUpForm;
import com.example.login.entity.SignUp;
import com.example.login.repository.SignUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {
    @Autowired
    private SignUpRepository signUpRepository;

    public String insertUser(SignUpForm signUpForm){
        SignUp signUp = toEntity(signUpForm);
        if(signUp.getPw() == null){
            return "ID를 입력해 주세요";
        } else if (signUpRepository.existsById(signUp.getId())) {
            return "이미 등록된 ID가 있습니다.";
        } else {
            signUpRepository.save(signUp);
            return "회원가입 완료";
        }
    }

    private SignUp toEntity(SignUpForm signUpForm){
        return new SignUp(signUpForm.getId(), signUpForm.getPw());
    }
}
