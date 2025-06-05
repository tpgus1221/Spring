package com.example.login.service;

import com.example.login.dto.SignUpForm;
import com.example.login.dto.SignUpReturn;
import com.example.login.entity.SignUp;
import com.example.login.repository.SignUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {
    @Autowired
    private SignUpRepository signUpRepository;

    public SignUpReturn insertUser(SignUpForm signUpForm){
        SignUp signUp = toEntity(signUpForm);
        SignUpReturn signUpReturn = toDto(signUpForm);
        if(signUp.getId() == null){
            signUpReturn.setIsnull(true);
            return signUpReturn;
        } else if (signUpRepository.existsById(signUp.getId())) {
            signUpReturn.setIsdupl(true);
            return signUpReturn;
        } else {
            signUpRepository.save(signUp);
            return signUpReturn;
        }
    }

    private SignUp toEntity(SignUpForm signUpForm){
        return new SignUp(signUpForm.getId(), signUpForm.getPw());
    }

    private SignUpReturn toDto(SignUpForm signUpForm){
        return new SignUpReturn(signUpForm.getId(), signUpForm.getPw(), false, false);
    }
}
