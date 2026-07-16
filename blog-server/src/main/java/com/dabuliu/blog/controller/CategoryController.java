package com.dabuliu.blog.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dabuliu.blog.category.Category;
import com.dabuliu.blog.category.CategoryService;
import com.dabuliu.blog.category.CreateCategoryRequest;

import jakarta.validation.Valid;

@RestController
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @PostMapping("/categories")
    public void addCategory(
            @Valid @RequestBody CreateCategoryRequest request) {

        service.addCategory(request.name());
    }

    @GetMapping("/categories")
    public List<Category> findCategories() {
        return service.findAllCategories();
    }
}