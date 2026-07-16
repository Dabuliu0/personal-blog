package com.dabuliu.blog.article;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public List<Article> searchByTitle(
            String keyword,
            int page,
            int size) {

        String lowerKeyword = keyword.toLowerCase();

        return findAll().stream()
                .filter(article -> !article.isDeleted())
                .filter(article -> article.getTitle()
                        .toLowerCase()
                        .contains(lowerKeyword))
                .skip((long) page * size)
                .limit(size)
                .toList();
    }

    @Override
    public List<Article> findPage(int page, int size) {
        return findAll().stream()
                .filter(article -> !article.isDeleted())
                .skip((long) page * size)
                .limit(size)
                .toList();
    }

}