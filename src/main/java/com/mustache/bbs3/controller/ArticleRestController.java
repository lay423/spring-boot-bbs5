package com.mustache.bbs3.controller;

import com.mustache.bbs3.domain.dto.ArticleAddRequest;
import com.mustache.bbs3.domain.dto.ArticleAddResponse;
import com.mustache.bbs3.domain.dto.ArticleDto;
import com.mustache.bbs3.domain.dto.Match;
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

    @PostMapping
    public ResponseEntity<ArticleAddResponse> addArticle(ArticleAddRequest dto) {
        System.out.println(dto.getTitle()+dto.getContent());
        ArticleAddResponse articleAddResponse = articleService.add(dto);
        System.out.println(articleAddResponse.getId()+articleAddResponse.getTitle()+articleAddResponse.getContent());
        return ResponseEntity.ok().body(articleAddResponse);
    }

    @PostMapping("/match")
    public ResponseEntity<Match> addMatch(Match dto) {
        System.out.println(dto.getName());
        return ResponseEntity.ok().body(dto);
    }
}
