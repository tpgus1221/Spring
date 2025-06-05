package com.example.login.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleForm {

    private Long id;
    private String title;
    private String content;
    private String author;
}


//컨트롤러에서 쓰는건 dto로 사용하게 바꾸기
