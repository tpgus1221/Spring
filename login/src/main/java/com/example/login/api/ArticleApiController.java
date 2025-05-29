package com.example.login.api;

import com.example.login.dto.ArticleForm;
import com.example.login.dto.ArticleReturn;
import com.example.login.service.ArticleService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticleApiController {

    @Autowired
    private ArticleService articleService;

    @PostMapping("/api/articles")
    public ArticleForm writeArticles(@RequestBody ArticleForm articleForm){//파라미터에 HttpSession session
        /*
        String author = (String) session.getAttribute("id");
        if (author == null) {
            throw new IllegalStateException("로그인이 필요한 기능입니다.");
        }
        articleForm.setAuthor(author);
         */
        return articleService.writeArticle(articleForm);
    }

    @GetMapping("/api/articles")
    public List<ArticleForm> showArticles(){
        return articleService.findAll();
    }

    @GetMapping("/api/articles/{id}")
    public ArticleReturn showDetail(@PathVariable Long id, HttpSession session){

        ArticleForm articleForm = articleService.findById(id);
        String loginId = (String) session.getAttribute("id");


        return new ArticleReturn(articleForm.getId(),
                articleForm.getTitle(),
                articleForm.getContent(),
                articleForm.getAuthor(),
                articleService.isAuthor(loginId, articleForm.getAuthor()));
    }

    @PatchMapping("/api/articles/{id}")
    public ArticleForm updateArticles(@PathVariable Long id, @RequestBody ArticleForm articleForm){
        return articleService.updateArticle(id, articleForm);
    }

    @DeleteMapping("/api/articles/{id}")
    public void deleteArticle(@PathVariable Long id){
        articleService.deleteArticle(id);
    }

}
