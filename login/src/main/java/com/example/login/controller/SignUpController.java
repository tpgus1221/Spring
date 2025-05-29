package com.example.login.controller;

import com.example.login.dto.SignUpForm;
import com.example.login.service.SignUpService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignUpController {
    private SignUpService signUpService;

    public SignUpController(SignUpService signUpService){
        this.signUpService = signUpService;
    }

    @GetMapping("/signup")
    public String showLoginForm(Model model) {
        model.addAttribute("result", "");
        return "signup";
    }

    @PostMapping("/signup")
    public String signUp(SignUpForm signUpForm, Model model){
        String result = signUpService.insertUser(signUpForm);

        model.addAttribute("result", result);//결과 메시지 출력
        return "login";

    }
}
