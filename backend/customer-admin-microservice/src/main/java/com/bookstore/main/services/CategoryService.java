package com.bookstore.main.services;

import com.bookstore.main.models.Category;
import com.bookstore.main.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category addCategory(String name) {
        Category category = new Category();
        category.setName(name);
        return categoryRepository.save(category);
    }

    public Category getCategory(Long id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Category not found")
        );
    }

    public Category updateCategory(Long id, String name) {
        Category category = getCategory(id);
        category.setName(name);
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        if(categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
        }
        else {
            throw new RuntimeException("Category not found");
        }
    }

    public int count(){
        return (int) categoryRepository.count();
    }

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

}
