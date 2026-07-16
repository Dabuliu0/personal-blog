package com.dabuliu.blog.category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    void save(Category category);

    Optional<Category> findById(long id);

    List<Category> findAll();

    boolean existsByName(String name);
}