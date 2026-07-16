package com.dabuliu.blog.category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataCategoryJpaRepository
        extends JpaRepository<Category, Long> {

    boolean existsByNameIgnoreCase(String name);
}