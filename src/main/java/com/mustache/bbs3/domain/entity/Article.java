package com.mustache.bbs3.domain.entity;

import com.mustache.bbs3.domain.dto.ArticleDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Entity
@NoArgsConstructor
@Getter
@AllArgsConstructor
public class Article {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String content;

    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public static ArticleDto of(Article article){
        return new ArticleDto(article.getId(), article.getTitle(), article.getContent());
    }
}
