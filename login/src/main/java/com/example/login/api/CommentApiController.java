package com.example.login.api;

import com.example.login.dto.CommentForm;
import com.example.login.service.ArticleService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
public class CommentApiController {
    @Autowired
    private ArticleService articleService;

    @PostMapping("articles/{id}/comments")
    public ResponseEntity<CommentForm> addComments(@PathVariable("id") Long articleId,
                                                   @RequestParam String loginId,
                                                   @RequestBody CommentForm commentForm){

        commentForm.setAuthor(loginId);
        commentForm.setArticleId(articleId);
        articleService.addComments(commentForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(commentForm);
    }

    @PatchMapping("/articles/{articleId}/comments/{commentId}")
    public ResponseEntity<CommentForm> editComments(@RequestParam String loginId,
                                                    @PathVariable Long articleId,
                                                    @PathVariable Long commentId,
                                                    @RequestBody CommentForm commentForm){

        commentForm.setArticleId(articleId);
        commentForm.setCommentId(commentId);
        commentForm.setAuthor(loginId);
        articleService.updateComments(articleId, commentId, commentForm, loginId);
        return ResponseEntity.ok().body(commentForm);
    }


    @DeleteMapping("articles/{articleId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long articleId,
                                              @PathVariable Long commentId){
        articleService.deleteComments(articleId, commentId);
        return ResponseEntity.noContent().build();
    }
}
