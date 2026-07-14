package com.dabuliu.blog.controller;

import com.dabuliu.blog.dto.ArticleUpdateRequest;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dabuliu.blog.article.Article;
import com.dabuliu.blog.article.ArticleService;
import com.dabuliu.blog.exception.ArticleNotFoundException;

@RestController
public class ArticleController {

    private final ArticleService service;

    public ArticleController(ArticleService service) {
        this.service = service;
    }

    @GetMapping("/articles")
    public List<Article> findArticles() {
        return service.findAllArticles();
    }

    @GetMapping("/articles/{id}")
    public Article findById(@PathVariable long id) {

        return service.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException(id));
    }

    @PostMapping("/articles")
    public void addArticle(@RequestBody Article article) {
        service.addArticle(article);
    }

    @PutMapping("/articles/{id}")
    public Article updateArticle(
            @PathVariable long id,
            @RequestBody ArticleUpdateRequest request) {

        return service.updateArticle(
                id,
                request.getTitle(),
                request.getContent(),
                request.isPublished());
    }

    @DeleteMapping("/articles/{id}")
    public void deleteArticle(@PathVariable long id) {

        service.deleteArticle(id);
    }
}