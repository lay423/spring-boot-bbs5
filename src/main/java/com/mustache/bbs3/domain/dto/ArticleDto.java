package com.mustache.bbs3.domain.dto;

import com.mustache.bbs3.domain.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ArticleDto {
    private Long id;
    private String title;
    private String content;

    Article toEntity(){
        return new Article(this.title, this.content);
    }
}
