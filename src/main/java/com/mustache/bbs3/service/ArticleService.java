package com.mustache.bbs3.service;

import com.mustache.bbs3.domain.entity.Article;
import com.mustache.bbs3.domain.entity.Hospital;
import com.mustache.bbs3.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article getArticle(Long id) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        Article article = optionalArticle.get();
        return article;
    }
}
