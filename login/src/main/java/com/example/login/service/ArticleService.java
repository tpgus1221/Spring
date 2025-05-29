package com.example.login.service;

import com.example.login.dto.ArticleForm;
import com.example.login.entity.Article;
import com.example.login.repository.ArticleRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    private Article toEntity(ArticleForm articleForm){
        //dto 엔티티화
        return new Article(null, articleForm.getTitle(), articleForm.getContent(), articleForm.getAuthor());
    }

    public ArticleForm writeArticle(ArticleForm articleForm){
        Article article = toEntity(articleForm);
        articleRepository.save(article);
        return new ArticleForm(article.getId(), article.getTitle(), article.getContent(), article.getAuthor());
    }

    public List<ArticleForm> findAll(){
        //목록의 글 전부 리턴
        List<Article> articles = articleRepository.findAll();
        List<ArticleForm> articleForms = new ArrayList<>();

        for(Article article : articles){
            articleForms.add(new ArticleForm(article.getId(), article.getTitle(), article.getContent(), article.getAuthor()));
        }
        return articleForms;
    }

    public ArticleForm findById(Long id){
        //id에 해당하는 튜플(글) 반환
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return new ArticleForm(article.getId(), article.getTitle(), article.getContent(), article.getAuthor());
    }

    public boolean isAuthor(String loginId, String articleId){
        //현재 이용자와 글 작성자가 같은 사람인지 비교
        if (loginId == null)
            loginId = "";
        return loginId.equals(articleId);
    }

    public ArticleForm updateArticle(Long id, ArticleForm articleForm){
        //글 수정 기능
        Article update = articleRepository.findById(id).orElse(null);

        if (update != null){
            update.setTitle(articleForm.getTitle());
            update.setContent(articleForm.getContent());
            articleRepository.save(update);
        }
        return new ArticleForm(update.getId(), update.getTitle(), update.getContent(), update.getAuthor());
    }

    public boolean deleteArticle(Long id){
        //글 삭제
        try{
            articleRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }

    }


}
