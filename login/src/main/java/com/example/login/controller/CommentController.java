package com.example.login.controller;

import com.example.login.dto.CommentForm;
import com.example.login.service.ArticleService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentController {

    @Autowired
    private ArticleService articleService;

    @PostMapping("articles/{id}/comments")
    public String addComments(@PathVariable("id") Long articleId,
                              CommentForm commentForm,
                              HttpSession session){
        String loginId = (String) session.getAttribute("id");
        commentForm.setAuthor(loginId);
        commentForm.setArticleId(articleId);
        articleService.addComments(commentForm);
        return "redirect:/articles/" + articleId;
    }

    @PostMapping("articles/{articleId}/comments/{commentId}/edit")
    public String editComments(@PathVariable Long articleId,
                               @PathVariable Long commentId,
                               CommentForm commentForm,
                               HttpSession session){
        String loginId = (String) session.getAttribute("id");
        commentForm.setCommentId(commentId);
        articleService.updateComments(articleId, commentId, commentForm, loginId);
        return "redirect:/articles/" + articleId;
    }


    @PostMapping("articles/{articleId}/comments/{commentId}/delete")
    public String deleteComment(@PathVariable Long articleId,
                                @PathVariable Long commentId){
        articleService.deleteComments(articleId, commentId);
        return "redirect:/articles/" + articleId;
    }


}

