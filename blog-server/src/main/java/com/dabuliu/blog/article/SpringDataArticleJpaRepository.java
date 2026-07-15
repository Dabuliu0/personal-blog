package com.dabuliu.blog.article;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataArticleJpaRepository
        extends JpaRepository<Article, Long> {
}