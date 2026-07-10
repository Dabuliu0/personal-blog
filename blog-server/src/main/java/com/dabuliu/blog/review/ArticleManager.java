package com.dabuliu.blog.review;

import java.util.ArrayList;
import java.util.List;

public class ArticleManager {

    private final List<Article> articles = new ArrayList<>();

    public void addArticle(Article article) {
        // TODO 1：将文章加入 articles
        articles.add(article);
    }

    public Article findById(long id) {
        for (Article article : articles) {
            // TODO 2：如果文章 id 等于参数 id，就返回该文章
            if (article.getId() == id) {
                return article;
            }
        }
        

        // 全部查完仍未找到
        return null;
    }

    public List<Article> findPublishedArticles() {
        List<Article> result = new ArrayList<>();

        for (Article article : articles) {
            // TODO 3：把已发布文章加入 result
            if (article.isPublished()) {
                result.add(article);
            }
        }

        return result;
    }

    public int getArticleCount() {
        // TODO 4：返回文章数量
        return articles.size();
    }
}