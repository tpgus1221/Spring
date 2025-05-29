package com.example.login.service;

import com.example.login.dto.LoginForm;
import com.example.login.entity.Login;
import com.example.login.repository.SignUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private SignUpRepository signUpRepository;

    public boolean compare(LoginForm loginForm){
            return signUpRepository.findByIdAndPw(loginForm.getId(), loginForm.getPw()).isPresent();
    }
}
