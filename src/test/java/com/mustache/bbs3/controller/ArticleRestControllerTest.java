package com.mustache.bbs3.controller;

import com.mustache.bbs3.domain.entity.Article;
import com.mustache.bbs3.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ArticleRestController.class)
class ArticleRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ArticleService articleService;

    @Test
    void jsonResponse() throws Exception {
        //{"id":2,"title":"내 이름은","content":"황준하"}
        Article article = new Article(2L, "내 이름은", "황준하");
        given(articleService.getArticle(2L)).willReturn(article);

        Long articleId = 2L;
        String url = String.format("/api/v1/articles/%d", articleId);

        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.title").value("내 이름은"))
                .andDo(print());

        verify(articleService).getArticle(articleId);

    }
}