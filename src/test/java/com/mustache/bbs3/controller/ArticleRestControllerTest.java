package com.mustache.bbs3.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mustache.bbs3.domain.dto.ArticleAddRequest;
import com.mustache.bbs3.domain.dto.ArticleAddResponse;
import com.mustache.bbs3.domain.entity.Article;
import com.mustache.bbs3.service.ArticleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    @Test
    @DisplayName("글 등록이 잘 되는지")
    void add() throws Exception {
        ArticleAddRequest dto = new ArticleAddRequest("제목입니다.", "내용입니다.");

        given(articleService.add(any())).willReturn(new ArticleAddResponse(1L, dto.getTitle(), dto.getContent()));

        mockMvc.perform(post("/api/v1/articles/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(new ArticleAddRequest("제목입니다.", "내용입니다.")))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.content").exists())
                .andDo(print());

        verify(articleService).add(dto);

    }
}