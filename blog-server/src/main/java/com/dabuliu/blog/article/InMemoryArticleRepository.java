package com.dabuliu.blog.article;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public class InMemoryArticleRepository implements ArticleRepository {

    private final List<Article> articles = new ArrayList<>();

    @Override
    public void save(Article article) {
        articles.add(article);
    }

    @Override
    public Optional<Article> findById(long id) {
        for (Article article : articles) {
            if (article.getId() == id) {
                return Optional.of(article);
            }
        }

        return Optional.empty();
    }

    @Override
    public List<Article> findAll() {
        return articles;
    }

}