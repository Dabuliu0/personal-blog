package com.dabuliu.blog.category;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class CategoryServiceTest {

    @Test
    void shouldAddCategory() {
        InMemoryCategoryRepository repository =
                new InMemoryCategoryRepository();

        CategoryService service =
                new CategoryService(repository);

        service.addCategory("Java");

        var categories = repository.findAll();

        assertEquals(1, categories.size());
        assertEquals("Java", categories.get(0).getName());
    }

    @Test
    void shouldRejectDuplicateCategoryIgnoringCase() {
        InMemoryCategoryRepository repository =
                new InMemoryCategoryRepository();

        CategoryService service =
                new CategoryService(repository);

        service.addCategory("Java");

        assertThrows(
                IllegalArgumentException.class,
                () -> service.addCategory("java"));
    }
}