package com.dabuliu.blog.article;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataArticleJpaRepository
                extends JpaRepository<Article, Long> {
        Page<Article> findByTitleContainingIgnoreCaseAndDeletedFalse(
        String keyword,
        Pageable pageable);
        Page<Article> findByDeletedFalse(Pageable pageable);
}