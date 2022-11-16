package com.mustache.bbs3.controller;

import com.mustache.bbs3.domain.dto.ArticleAddRequest;
import com.mustache.bbs3.domain.dto.ArticleAddResponse;
import com.mustache.bbs3.domain.dto.ArticleDto;
import com.mustache.bbs3.domain.entity.Article;
import com.mustache.bbs3.service.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/articles")
public class ArticleRestController {

    private final ArticleService articleService;

    public ArticleRestController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> get(@PathVariable Long id) {
       Article article = articleService.getArticle(id);
        return ResponseEntity.ok().body(article);
    }

    @PostMapping("/add")
    public ArticleAddResponse add(ArticleAddRequest request) {
        ArticleAddResponse articleAddResponse = articleService.add(request);
        return articleAddResponse;
    }
}