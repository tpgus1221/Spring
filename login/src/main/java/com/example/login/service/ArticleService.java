package com.example.login.service;

import com.example.login.dto.ArticleForm;
import com.example.login.dto.ArticleReturn;
import com.example.login.dto.CommentForm;
import com.example.login.dto.CommentReturn;
import com.example.login.entity.Article;
import com.example.login.entity.Comment;
import com.example.login.repository.ArticleRepository;
import com.example.login.repository.CommentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CommentRepository commentRepository;


    private Article toEntity(ArticleForm articleForm){
        //dto 엔티티화
        return new Article(null, articleForm.getTitle(), articleForm.getContent(), articleForm.getAuthor());
    }

    public ArticleForm writeArticle(ArticleForm articleForm){
        Article article = toEntity(articleForm);
        articleRepository.save(article);
        return articleForm;
    }

    public List<ArticleReturn> findAll(){
        //목록의 글 전부 리턴
        return articleRepository.findAll().stream()
                .map(article -> new ArticleReturn(
                        article.getId(),
                        article.getTitle(),
                        article.getContent(),
                        article.getAuthor(),
                        false
                ))
                .collect(Collectors.toList());
    }

    public ArticleReturn findById(Long id, String loginId){
        //id에 해당하는 튜플(글) 반환
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        boolean isAuthor = isAuthor(loginId, article.getAuthor());
        List<CommentReturn> comments = getCommentsForArticle(id, loginId);
        return new ArticleReturn(article.getId(), article.getTitle(), article.getContent(), article.getAuthor(), isAuthor, comments);
    }

    public boolean isAuthor(String loginId, String id){
        //현재 이용자와 글 작성자가 같은 사람인지 비교

        return loginId != null && loginId.equals(id);
    }

    public ArticleReturn updateArticle(Long id, ArticleForm articleForm){
        //글 수정 기능
        Article update = articleRepository.findById(id).orElse(null);

        if (update != null){
            update.setTitle(articleForm.getTitle());
            update.setContent(articleForm.getContent());
            articleRepository.save(update);
        }
        return new ArticleReturn(update.getId(), update.getTitle(), update.getContent(), update.getAuthor(), true);
    }

    @Transactional
    public boolean deleteArticle(Long id){
        //글 삭제
        try{
            commentRepository.deleteByArticleId(id); // 해당 글의 댓글 전부 삭제
            articleRepository.deleteById(id); // 해당 글 삭제
            return true;
        }catch (Exception e){
            return false;
        }
    }

    private Comment toEntity(CommentForm commentForm, Article article, String loginId){
        //엔티티화
        return new Comment(null, article, loginId, commentForm.getContent());
    }

    public CommentForm addComments(CommentForm commentForm){
        //댓글 추가
        Article article = articleRepository.findById(commentForm.getArticleId())
                .orElseThrow(() -> new IllegalArgumentException("해당 글이 존재하지 않습니다."));
        Comment comment = toEntity(commentForm, article, commentForm.getAuthor());
        commentRepository.save(comment);
        return commentForm;
    }

    public List<CommentReturn> getCommentsForArticle(Long articleId, String loginId){
        //댓글 리스트 리턴
        List<Comment> comments = commentRepository.findByArticleId(articleId);

        return comments.stream()
                .map(comment -> {
                    boolean isAuthor = isAuthor(loginId, comment.getAuthor());
                    return new CommentReturn(comment.getId(),
                            articleId,
                            comment.getAuthor(),
                            comment.getContent(),
                            isAuthor
                    );
                })
                .collect(Collectors.toList());
    }

    public CommentReturn updateComments(Long articleId, Long commentId, CommentForm commentForm, String loginId){
        //댓글 수정
        Comment update = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다."));
        if (!update.getArticle().getId().equals(articleId)) {
            throw new IllegalArgumentException("댓글이 속한 게시글이 일치하지 않습니다.");
        }
        update.setContent(commentForm.getContent());
        commentRepository.save(update);
        boolean isAuthor = isAuthor(loginId, update.getAuthor());
        return new CommentReturn(articleId, commentId, update.getAuthor(), update.getContent(), isAuthor);
    }

    public boolean deleteComments(Long articleId, Long commentId){
        //댓글 삭제
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다."));
        if (!comment.getArticle().getId().equals(articleId)) {
            throw new IllegalArgumentException("댓글이 속한 게시글이 일치하지 않습니다.");
        }
        commentRepository.deleteById(commentId);
        System.out.println("댓글 삭제 성공");
        return true;
    }
}