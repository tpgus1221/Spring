package com.example.login.api;

import com.example.login.dto.ArticleForm;
import com.example.login.dto.ArticleReturn;
import com.example.login.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/api")
@RestController
public class ArticleApiController {

    @Autowired
    private ArticleService articleService;

    @PostMapping("/articles")
    public ResponseEntity<?> writeArticles(@RequestBody ArticleForm articleForm,
                                           @RequestParam String loginId){
        //작성 버튼 눌렀을때 DB에 저장하기
        //REST 완
        articleForm.setAuthor(loginId);
        articleService.writeArticle(articleForm);   //서비스의 글 작성 메소드
        return ResponseEntity.status(HttpStatus.CREATED).body("글 작성 완료");
    }

    @GetMapping("/articles")
    public ResponseEntity<List<ArticleReturn>> showArticles(){
        //REST 완
        List<ArticleReturn> articleReturns = articleService.findAll();
        return ResponseEntity.ok(articleReturns);
    }

    @GetMapping("/articles/{id}")
    public ResponseEntity<?> showDetail(@PathVariable("id") Long articleId,
                                        @RequestParam String loginId){
        //REST 완

        try {
            ArticleReturn articleReturn = articleService.findById(articleId, loginId);
            return ResponseEntity.ok(articleReturn);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류 발생");
        }

    }

    @PatchMapping("/articles/{id}")
    public ResponseEntity<ArticleReturn> updateArticles(@PathVariable Long id,
                                                        @RequestBody ArticleForm articleForm){
        //REST 완, 작성자가 아니어도 수정 가능한 상태
        ArticleReturn articleReturn = articleService.updateArticle(id, articleForm);
        return ResponseEntity.ok(articleReturn);
    }

    @DeleteMapping("/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id){
        //REST 완, 작성자가 아니어도 삭제 가능한 상태
        //글 삭제 시 댓글도 전부 삭제하는 기능 추가할 것
        articleService.deleteArticle(id);
        return ResponseEntity.noContent().build();
    }

}
