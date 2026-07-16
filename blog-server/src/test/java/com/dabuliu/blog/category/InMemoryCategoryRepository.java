package com.dabuliu.blog.category;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class InMemoryCategoryRepository implements CategoryRepository {

    private final List<Category> categories = new ArrayList<>();

    @Override
    public void save(Category category) {
        categories.add(category);
    }

    @Override
    public Optional<Category> findById(long id) {
        return categories.stream()
                .filter(category -> category.getId() != null)
                .filter(category -> category.getId() == id)
                .findFirst();
    }

    @Override
    public List<Category> findAll() {
        return List.copyOf(categories);
    }

    @Override
    public boolean existsByName(String name) {
        return categories.stream()
                .anyMatch(category -> category.getName()
                        .equalsIgnoreCase(name));
    }
}