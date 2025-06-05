package com.example.login.controller;

import com.example.login.dto.ArticleForm;
import com.example.login.dto.ArticleReturn;
import com.example.login.dto.CommentForm;
import com.example.login.dto.CommentReturn;
import com.example.login.entity.Article;
import com.example.login.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;

import java.util.List;

@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/articles/new")
    public String showWriteForm(){
        //글쓰기 화면 보여주기
        return "write";
    }

    @PostMapping("/articles")
    public String writeArticles(ArticleForm articleForm, HttpSession session, Model model){
        //작성 버튼 눌렀을때 DB에 저장하기
        String author = (String) session.getAttribute("id");

        articleForm.setAuthor(author);
        if (author == null) {
            model.addAttribute("errorMessage", "로그인이 필요한 기능입니다.");
            return "error";
        }
        articleService.writeArticle(articleForm);   //서비스의 글 작성 메소드
        return "redirect:/articles";
    }

    @GetMapping("/articles")
    public String showArticles(Model model){
        //글 목록 전부 나열
        List<ArticleReturn> articleList = articleService.findAll();
        model.addAttribute("articles", articleList);
        return "list";
    }

    @GetMapping("/articles/{id}")
    public String showDetail(@PathVariable Long id, Model model, HttpSession session){
        //클릭된 글의 세부 내용 표시
        String loginId = (String) session.getAttribute("id");
        ArticleReturn articleReturn = articleService.findById(id, loginId);
        //List<CommentReturn> comments = articleService.getCommentsForArticle(id, loginId);

        model.addAttribute("title", articleReturn.getTitle());
        model.addAttribute("content", articleReturn.getContent());
        model.addAttribute("author", articleReturn.getAuthor());
        model.addAttribute("articleId", articleReturn.getId());
        model.addAttribute("comments", articleReturn.getComments());//dto의 comments로 대체
        model.addAttribute("isAuthor", articleReturn.isIsauthor());
        return "detail";
    }
    @GetMapping("/articles/{id}/edit")
    public String editArticles(@PathVariable Long id, Model model, HttpSession session){
        //수정버튼 클릭 시 수정화면 보여주기
        String loginId = (String) session.getAttribute("id");
        ArticleReturn articleReturn = articleService.findById(id, loginId);
        model.addAttribute("id", articleReturn.getId());
        model.addAttribute("title", articleReturn.getTitle());
        model.addAttribute("content", articleReturn.getContent());

        return "edit";
    }
    @PostMapping("/articles/{id}/update")
    public String updateArticles(@PathVariable Long id, ArticleForm articleForm){
        //수정완료 버튼 누를시 DB에 업데이트하기
        articleService.updateArticle(id, articleForm);
        return "redirect:/articles/" + id;
    }

    @PostMapping("/articles/{id}/delete")
    public String deleteArticle(@PathVariable Long id){
        //삭제하기
        articleService.deleteArticle(id);
        return "redirect:/articles";
    }

}
