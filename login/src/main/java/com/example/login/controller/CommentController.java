package com.example.login.controller;

import com.example.login.dto.CommentForm;
import com.example.login.service.ArticleService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommentController {

    @Autowired
    private ArticleService articleService;

    @PostMapping("articles/{id}/comments")
    public String addComments(@PathVariable("id") Long articleId, CommentForm commentForm, HttpSession session){
        String loginId = (String) session.getAttribute("id");
        commentForm.setAuthor(loginId);
        commentForm.setArticleId(articleId);
        articleService.addComments(articleId,loginId,commentForm);
        return "redirect:/articles/" + articleId;
    }

    @PostMapping("/comments/{id}/edit")
    public String editComments(@PathVariable("id") Long commentId, CommentForm commentForm){

        articleService.updateComments(commentForm.getArticleId(), commentId, commentForm);
        return "redirect:/articles/" + commentForm.getArticleId();
    }


    @PostMapping("/comments/{id}/delete")
    public String deleteComment(@PathVariable("id") Long commentId, CommentForm commentForm){
        articleService.deleteComments(commentId);
        Long  articleId = commentForm.getArticleId();
        return "redirect:/articles/" + articleId;
    }
}

