package com.bookstore.main.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bookstore.main.models.Category;
import com.bookstore.main.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CategoryService.class})
@ExtendWith(SpringExtension.class)
class CategoryServiceTest {
    @MockBean
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    @Test
    void testAddCategory() {
        Category category = new Category();
        category.setId(123L);
        category.setName("Name");
        when(this.categoryRepository.save((Category) any())).thenReturn(category);
        assertSame(category, this.categoryService.addCategory("Name"));
        verify(this.categoryRepository).save((Category) any());
        assertTrue(this.categoryService.getAllCategories().isEmpty());
    }

    @Test
    void testAddCategory2() {
        Category category = new Category();
        category.setId(123L);
        category.setName("Name");
        when(this.categoryRepository.save((Category) any())).thenReturn(category);
        assertSame(category, this.categoryService.addCategory("Name"));
        verify(this.categoryRepository).save((Category) any());
        assertTrue(this.categoryService.getAllCategories().isEmpty());
    }

    @Test
    void testGetCategory() {
        Category category = new Category();
        category.setId(123L);
        category.setName("Name");
        Optional<Category> ofResult = Optional.of(category);
        when(this.categoryRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(category, this.categoryService.getCategory(123L));
        verify(this.categoryRepository).findById((Long) any());
        assertTrue(this.categoryService.getAllCategories().isEmpty());
    }

    @Test
    void testGetCategory2() {
        when(this.categoryRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> this.categoryService.getCategory(123L));
        verify(this.categoryRepository).findById((Long) any());
    }

    @Test
    void testGetCategory3() {
        Category category = new Category();
        category.setId(123L);
        category.setName("Name");
        Optional<Category> ofResult = Optional.of(category);
        when(this.categoryRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(category, this.categoryService.getCategory(123L));
        verify(this.categoryRepository).findById((Long) any());
        assertTrue(this.categoryService.getAllCategories().isEmpty());
    }

    @Test
    void testGetCategory4() {
        when(this.categoryRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> this.categoryService.getCategory(123L));
        verify(this.categoryRepository).findById((Long) any());
    }

    @Test
    void testUpdateCategory() {
        Category category = new Category();
        category.setId(123L);
        category.setName("Name");
        Optional<Category> ofResult = Optional.of(category);

        Category category1 = new Category();
        category1.setId(123L);
        category1.setName("Name");
        when(this.categoryRepository.save((Category) any())).thenReturn(category1);
        when(this.categoryRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(category1, this.categoryService.updateCategory(123L, "Name"));
        verify(this.categoryRepository).findById((Long) any());
        verify(this.categoryRepository).save((Category) any());
        assertTrue(this.categoryService.getAllCategories().isEmpty());
    }

    @Test
    void testUpdateCategory2() {
        Category category = new Category();
        category.setId(123L);
        category.setName("Name");
        when(this.categoryRepository.save((Category) any())).thenReturn(category);
        when(this.categoryRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> this.categoryService.updateCategory(123L, "Name"));
        verify(this.categoryRepository).findById((Long) any());
    }

    @Test
    void testUpdateCategory3() {
        Category category = new Category();
        category.setId(123L);
        category.setName("Name");
        Optional<Category> ofResult = Optional.of(category);

        Category category1 = new Category();
        category1.setId(123L);
        category1.setName("Name");
        when(this.categoryRepository.save((Category) any())).thenReturn(category1);
        when(this.categoryRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(category1, this.categoryService.updateCategory(123L, "Name"));
        verify(this.categoryRepository).findById((Long) any());
        verify(this.categoryRepository).save((Category) any());
        assertTrue(this.categoryService.getAllCategories().isEmpty());
    }

    @Test
    void testUpdateCategory4() {
        Category category = new Category();
        category.setId(123L);
        category.setName("Name");
        when(this.categoryRepository.save((Category) any())).thenReturn(category);
        when(this.categoryRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> this.categoryService.updateCategory(123L, "Name"));
        verify(this.categoryRepository).findById((Long) any());
    }

    @Test
    void testDeleteCategory() {
        doNothing().when(this.categoryRepository).deleteById((Long) any());
        when(this.categoryRepository.existsById((Long) any())).thenReturn(true);
        this.categoryService.deleteCategory(123L);
        verify(this.categoryRepository).deleteById((Long) any());
        verify(this.categoryRepository).existsById((Long) any());
        assertTrue(this.categoryService.getAllCategories().isEmpty());
    }

    @Test
    void testDeleteCategory2() {
        doNothing().when(this.categoryRepository).deleteById((Long) any());
        when(this.categoryRepository.existsById((Long) any())).thenReturn(false);
        assertThrows(RuntimeException.class, () -> this.categoryService.deleteCategory(123L));
        verify(this.categoryRepository).existsById((Long) any());
    }

    @Test
    void testDeleteCategory3() {
        doNothing().when(this.categoryRepository).deleteById((Long) any());
        when(this.categoryRepository.existsById((Long) any())).thenReturn(true);
        this.categoryService.deleteCategory(123L);
        verify(this.categoryRepository).deleteById((Long) any());
        verify(this.categoryRepository).existsById((Long) any());
        assertTrue(this.categoryService.getAllCategories().isEmpty());
    }

    @Test
    void testDeleteCategory4() {
        doNothing().when(this.categoryRepository).deleteById((Long) any());
        when(this.categoryRepository.existsById((Long) any())).thenReturn(false);
        assertThrows(RuntimeException.class, () -> this.categoryService.deleteCategory(123L));
        verify(this.categoryRepository).existsById((Long) any());
    }

    @Test
    void testCount() {
        when(this.categoryRepository.count()).thenReturn(3L);
        assertEquals(3, this.categoryService.count());
        verify(this.categoryRepository).count();
        assertTrue(this.categoryService.getAllCategories().isEmpty());
    }

    @Test
    void testCount2() {
        when(this.categoryRepository.count()).thenReturn(3L);
        assertEquals(3, this.categoryService.count());
        verify(this.categoryRepository).count();
        assertTrue(this.categoryService.getAllCategories().isEmpty());
    }

    @Test
    void testGetAllCategories() {
        ArrayList<Category> categoryList = new ArrayList<>();
        when(this.categoryRepository.findAll()).thenReturn(categoryList);
        List<Category> actualAllCategories = this.categoryService.getAllCategories();
        assertSame(categoryList, actualAllCategories);
        assertTrue(actualAllCategories.isEmpty());
        verify(this.categoryRepository).findAll();
    }

    @Test
    void testGetAllCategories2() {
        ArrayList<Category> categoryList = new ArrayList<>();
        when(this.categoryRepository.findAll()).thenReturn(categoryList);
        List<Category> actualAllCategories = this.categoryService.getAllCategories();
        assertSame(categoryList, actualAllCategories);
        assertTrue(actualAllCategories.isEmpty());
        verify(this.categoryRepository).findAll();
    }
}

