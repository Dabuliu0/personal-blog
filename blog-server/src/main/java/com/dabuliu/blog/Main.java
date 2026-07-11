package com.dabuliu.blog;

import com.dabuliu.blog.article.Article;
import com.dabuliu.blog.article.ArticleRepository;
import com.dabuliu.blog.article.ArticleService;

public class Main {

    public static void main(String[] args) {
        ArticleRepository repository = new ArticleRepository();
        ArticleService manager = new ArticleService(repository);
        
        manager.addArticle(new Article(1L, "Java 学习记录", "1", false));
        manager.addArticle(new Article(2L, "Git 学习记录", "2", true));
        manager.addArticle(new Article(3L, "个人博客开发", "3", false));

        System.out.println("文章总数：" + manager.getArticleCount());

        manager.findById(2L)
                .ifPresentOrElse(
                        article -> System.out.println("找到的文章：" + article.getTitle()),
                        () -> System.out.println("没有找到文章")

                );

        manager.findById(99L)
                .ifPresentOrElse(
                        article -> System.out.println("找到的文章：" + article.getTitle()),
                        () -> System.out.println("没有找到文章")

                );

        Article updated = manager.updateArticle(
                2L,
                "测试",
                "新的正文",
                true);

        System.out.println("修改后的文章：" + updated);

    }
}