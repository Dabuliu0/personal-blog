package com.dabuliu.blog.article;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Repository
public class JpaArticleRepositoryAdapter implements ArticleRepository {

    private final SpringDataArticleJpaRepository jpaRepository;

    public JpaArticleRepositoryAdapter(
            SpringDataArticleJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void save(Article article) {
        jpaRepository.save(article);
    }

    @Override
    public Optional<Article> findById(long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public List<Article> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public List<Article> searchByTitle(String keyword) {
        return jpaRepository.findByTitleContainingIgnoreCaseAndDeletedFalse(keyword);
    }

    @Override
    public List<Article> findPage(int page, int size) {
        PageRequest pageRequest = PageRequest.of(
                page,
                size,
                Sort.by("id").ascending());

        return jpaRepository.findByDeletedFalse(pageRequest).getContent();
    }
}