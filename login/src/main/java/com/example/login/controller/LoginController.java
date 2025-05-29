package com.example.login.controller;

import com.example.login.dto.LoginForm;
import com.example.login.service.LoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService){
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(LoginForm loginForm, HttpSession session, Model model){

        boolean compare = loginService.compare(loginForm);

        if (compare) {
            session.setAttribute("id", loginForm.getId());
            return "redirect:/main";
        } else {
            model.addAttribute("errorMessage",
                    "아이디 또는 비밀번호가 틀렸습니다.");
            return "login";
        }
    }
}
