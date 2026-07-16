package com.dabuliu.blog.category;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public void addCategory(String name) {
        Category category = new Category(name);

        if (repository.existsByName(category.getName())) {
            throw new IllegalArgumentException("分类名称已经存在");
        }

        repository.save(category);
    }

    public List<Category> findAllCategories() {
        return repository.findAll();
    }

    public Category findCategoryById(long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "分类不存在，id=" + id));
    }
}