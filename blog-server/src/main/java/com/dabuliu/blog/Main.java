package com.dabuliu.blog;

import com.dabuliu.blog.article.Article;
import com.dabuliu.blog.article.ArticleRepository;
import com.dabuliu.blog.article.ArticleService;
import com.dabuliu.blog.article.InMemoryArticleRepository;
import com.dabuliu.blog.exception.DuplicateArticleIdException;

public class Main {

    public static void main(String[] args) {
        ArticleRepository repository = new InMemoryArticleRepository();
        ArticleService service = new ArticleService(repository);

        service.addArticle(new Article(3L, "个人博客开发", "3", false));
        service.addArticle(new Article(1L, "Java 学习记录", "1", false));
        service.addArticle(new Article(2L, "Git 学习记录", "2", true));

        try {
            service.addArticle(
                    new Article(1L, "重复文章", "测试正文", false));
        } catch (DuplicateArticleIdException e) {
            System.out.println("添加失败：" + e.getMessage());
        }

        service.findAllArticles()
                .forEach(article -> System.out.println(article.getId()));
    }
}