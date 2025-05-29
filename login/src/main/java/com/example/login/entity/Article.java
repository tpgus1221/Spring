package com.example.login.entity;

import com.example.login.dto.ArticleForm;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String content;
    private String author;

    public ArticleForm toForm(){
        return new ArticleForm(this.id, this.title, this.content, this.author);
    }
}
