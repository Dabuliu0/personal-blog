package com.dabuliu.blog.article;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {

    void save(Article article);

    Optional<Article> findById(long id);

    List<Article> findAll();

    List<Article> searchByTitle(String keyword, int page, int size);

    List<Article> findPage(int page, int size);
}