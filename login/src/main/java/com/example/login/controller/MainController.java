package com.example.login.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

@Controller
public class MainController {

    @GetMapping("/main")
    public String showMainForm(HttpSession session, Model model){
        String id = (String) session.getAttribute("id");
        model.addAttribute("id", id);
        return "main";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session, Model model){
        model.addAttribute("logoutMessage", "로그아웃 되었습니다.");
        session.invalidate();
        return "login";
    }
}
