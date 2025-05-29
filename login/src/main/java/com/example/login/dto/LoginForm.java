package com.example.login.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginForm {
    private String id;
    private String pw;
}
