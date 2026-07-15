package com.dabuliu.blog.config;

import com.dabuliu.blog.article.Article;
import com.dabuliu.blog.article.ArticleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ArticleService service;

    public DataInitializer(ArticleService service) {
        this.service = service;
    }

    @Override
    public void run(String... args) {

        if (service.getArticleCount() > 0) {
        return;
    }

        service.addArticle(
                new Article(
                        
                        "Java 学习记录",
                        "Spring Boot 入门",
                        true
                )
        );

        service.addArticle(
                new Article(
                
                        "博客开发记录",
                        "第一次接口开发",
                        false
                )
        );
    }
}