package com.kinga.onlineshop.api;

import com.kinga.onlineshop.dao.entity.Category;
import com.kinga.onlineshop.dto.CategoryDto;
import com.kinga.onlineshop.manager.CategoryManager;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
public class CategoryApi {
    CategoryManager categoryManager;
    ModelMapper modelMapper;

    @Autowired
    public CategoryApi(CategoryManager categoryManager, ModelMapper modelMapper) {
        this.categoryManager = categoryManager;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public Iterable<CategoryDto> getAll(){
        Iterable<Category> iterable = categoryManager.findAll();
        List<Category> list = new ArrayList<>();
        iterable.forEach(list::add);
        return list.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public CategoryDto getById(@PathVariable Long id){
        Optional<Category> optional = categoryManager.findById(id);
        CategoryDto categoryDto = convertToDto(optional.get());
        return categoryDto;
    }

    @PostMapping
    public CategoryDto addCategory(@RequestBody CategoryDto categoryDto){
        Category category = convertToEntity(categoryDto);
        Category createdCategory = categoryManager.save(category);
        return convertToDto(createdCategory);
    }

    @PutMapping("{id}")
    public CategoryDto updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto){
        categoryDto.setIdCategory(id);
        Category category = convertToEntity(categoryDto);
        Category createdCategory = categoryManager.save(category);
        return convertToDto(createdCategory);
    }

    @DeleteMapping("{id}")
    public void deleteCategory(@PathVariable Long id){
        categoryManager.deleteById(id);
    }

    private CategoryDto convertToDto(Category category){
        CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
        return categoryDto;
    }

    private Category convertToEntity(CategoryDto categoryDto){
        Category category = modelMapper.map(categoryDto, Category.class);
        return category;
    }
}
