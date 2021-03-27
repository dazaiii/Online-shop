package com.kinga.onlineshop.api;

import com.kinga.onlineshop.dao.entity.Category;
import com.kinga.onlineshop.manager.CategoryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryApi {
    CategoryManager categoryManager;

    @Autowired
    public CategoryApi(CategoryManager categoryManager) {
        this.categoryManager = categoryManager;
    }

    @GetMapping
    public Iterable<Category> getAll(){
        return categoryManager.findAll();
    }

    @GetMapping("{id}")
    public Optional<Category> getById(@PathVariable Long id){
        return categoryManager.findById(id);
    }

    @PostMapping
    public Category addCategory(@RequestBody Category category){
        return categoryManager.save(category);
    }

    @PutMapping
    public Category updateCategory(@RequestBody Category category){
        return categoryManager.save(category);
    }

    @DeleteMapping("{id}")
    public void deleteCategory(@PathVariable Long id){
        categoryManager.deleteById(id);
    }
}
