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
import org.springframework.web.bind.annotation.RequestParam;

import com.dabuliu.blog.article.Article;
import com.dabuliu.blog.article.ArticleService;
import com.dabuliu.blog.article.CreateArticleRequest;
import com.dabuliu.blog.exception.ArticleNotFoundException;
import jakarta.validation.Valid;
import com.dabuliu.blog.category.Category;
import com.dabuliu.blog.category.CategoryService;
import org.springframework.security.core.Authentication;

import com.dabuliu.blog.user.UserAccount;
import com.dabuliu.blog.user.UserService;

@RestController
public class ArticleController {

    private final ArticleService service;
    private final CategoryService categoryService;
    private final UserService userService;

    public ArticleController(
            ArticleService service,
            CategoryService categoryService,
            UserService userService) {

        this.service = service;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @GetMapping("/articles")
    public List<Article> findArticles(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {

        return service.findAllArticles(page, size);
    }

    @GetMapping("/articles/{id}")
    public Article findById(@PathVariable("id") long id) {

        return service.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException(id));
    }

    @GetMapping("/articles/search")
    public List<Article> searchArticles(
            @RequestParam("keyword") String keyword,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {

        return service.searchByTitle(keyword, page, size);
    }

    @PostMapping("/articles")
    public void addArticle(
            @Valid @RequestBody CreateArticleRequest request,
            Authentication authentication) {

        Category category = categoryService
                .findCategoryById(request.categoryId());

        UserAccount author = userService
                .findUserByUsername(
                        authentication.getName());

        Article article = new Article(
                request.title(),
                request.content(),
                request.published());

        service.addArticle(article, category, author);
    }

    @PutMapping("/articles/{id}")
    public Article updateArticle(
            @PathVariable("id") long id,
            @RequestBody ArticleUpdateRequest request,
            Authentication authentication) {

        UserAccount currentUser = userService
                .findUserByUsername(
                        authentication.getName());

        return service.updateOwnedArticle(
                id,
                request.getTitle(),
                request.getContent(),
                request.isPublished(),
                currentUser);
    }

    @DeleteMapping("/articles/{id}")
    public void deleteArticle(
            @PathVariable("id") long id,
            Authentication authentication) {

        UserAccount currentUser = userService
                .findUserByUsername(
                        authentication.getName());

        service.deleteOwnedArticle(
                id,
                currentUser);
    }
}