package com.example.login.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentForm {
   private Long articleId;//게시글 ID
   private Long commentId;//댓글 ID
   private String author; //댓글 작성자
   private String content; // 댓글 내용
}
