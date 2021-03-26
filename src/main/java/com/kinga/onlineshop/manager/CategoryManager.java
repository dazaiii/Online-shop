package com.kinga.onlineshop.manager;

import com.kinga.onlineshop.dao.CategoryRepo;
import com.kinga.onlineshop.dao.entity.Category;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryManager {
    private CategoryRepo categoryRepo;

    public CategoryManager(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public Optional<Category> findById(Long id){
        return categoryRepo.findById(id);
    }

    public Iterable<Category> findAll(){
        return categoryRepo.findAll();
    }

    public Category save(Category category){
        return categoryRepo.save(category);
    }

    public void deleteById(Long id){
        categoryRepo.deleteById(id);
    }
}
