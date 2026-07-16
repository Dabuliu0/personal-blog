package com.dabuliu.blog.category;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public class JpaCategoryRepositoryAdapter
        implements CategoryRepository {

    private final SpringDataCategoryJpaRepository jpaRepository;

    public JpaCategoryRepositoryAdapter(
            SpringDataCategoryJpaRepository jpaRepository) {

        this.jpaRepository = jpaRepository;
    }

    @Override
    public void save(Category category) {
        jpaRepository.save(category);
    }

    @Override
    public Optional<Category> findById(long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public List<Category> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public boolean existsByName(String name) {
        return jpaRepository.existsByNameIgnoreCase(name);
    }
}