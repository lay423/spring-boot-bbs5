package com.mustache.bbs3.controller;

import com.mustache.bbs3.domain.dto.ArticleDto;
import com.mustache.bbs3.domain.entity.Article;
import com.mustache.bbs3.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/articles")
@Slf4j
public class ArticleController {

    private final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping("/new")
    public String create() {
        return "articles/new";
    }

    @GetMapping("/list")
    public String selectList(Model model) {
        List<Article> articleList = articleRepository.findAll();
        model.addAttribute("articles", articleList);
        return "articles/list";
    }

    @GetMapping("")
    public String index() {
        return "redirect:/articles/list";
    }

    @GetMapping("/{id}")
    public String selectSingle(@PathVariable Long id, Model model) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        log.info(optionalArticle.get().getTitle());
        if (!optionalArticle.isEmpty()) {
            System.out.println("성공");
            model.addAttribute("article", optionalArticle.get());
            return "articles/show";
        } else {
            return "articles/error";
        }
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        log.info(optionalArticle.get().getTitle());
        if (optionalArticle.isPresent()) {
            System.out.println("성공");
            model.addAttribute("article", optionalArticle.get());
            return "articles/edit";
        } else {
            return "articles/error";
        }
    }

    @PostMapping("")
    public String articles(ArticleDto articleDto) {
        log.info(articleDto.getTitle());
        Article article = articleDto.toEntity();
        articleRepository.save(article);
        return "";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable Long id, ArticleDto articleDto, Model model) {
        Article article = articleRepository.save(articleDto.toEntity());
        model.addAttribute("article", article);
        return String.format("redirect:/articles/%d", article.getId());
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        articleRepository.deleteById(id);
        return String.format("redirect:/articles/list");
    }
}
