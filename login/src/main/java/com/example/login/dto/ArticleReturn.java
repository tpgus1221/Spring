package com.example.login.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleReturn {
    private Long id;
    private String title;
    private String content;
    private String author;
    
    private boolean isauthor = false;
}
