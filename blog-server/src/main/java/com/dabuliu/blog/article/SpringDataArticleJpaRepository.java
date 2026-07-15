package com.dabuliu.blog.article;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataArticleJpaRepository
                extends JpaRepository<Article, Long> {
        List<Article> findByTitleContainingIgnoreCaseAndDeletedFalse(String keyword);
}