package com.example.login.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
    private List<CommentReturn> comments;

    public ArticleReturn(Long id, String title, String content, String author, boolean isauthor){
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.isauthor = isauthor;
        this.comments = null;
    }

}
