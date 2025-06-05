package com.example.login.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SignUpReturn {
    private String id;
    private String pw;
    private boolean isnull;
    private boolean isdupl;
}
