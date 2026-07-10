package com.dabuliu.blog.review;



public class Main {

    public static void main(String[] args) {
        ArticleManager manager = new ArticleManager();
        manager.addArticle(new Article(1L, "Java 学习记录", false));
        manager.addArticle(new Article(2L, "Git 学习记录", true));
        manager.addArticle(new Article(3L, "个人博客开发", false));

        System.out.println("文章总数：" + manager.getArticleCount());

        Article found = manager.findById(2L);
        System.out.println("查询结果：" + found);

        System.out.println("已发布文章：");
        for (Article article : manager.findPublishedArticles()) {
            System.out.println(article);
        }

        
    }
}