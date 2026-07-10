package com.dabuliu.blog;
import com.dabuliu.blog.article.Article;
import com.dabuliu.blog.article.ArticleManager;

public class Main {

    public static void main(String[] args) {
        ArticleManager manager = new ArticleManager();
        manager.addArticle(new Article(1L, "Java 学习记录", false));
        manager.addArticle(new Article(2L, "Git 学习记录", true));
        manager.addArticle(new Article(3L, "个人博客开发", false));

        System.out.println("文章总数：" + manager.getArticleCount());

        manager.findById(2L)
        .ifPresentOrElse(
            article ->System.out.println("找到的文章："+article.getTitle()),
            () ->System.out.println("没有找到文章")

        );
        
        
        manager.findById(99L)
        .ifPresentOrElse(
            article ->System.out.println("找到的文章："+article.getTitle()),
            () ->System.out.println("没有找到文章")

        );

    }
}