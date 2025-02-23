package com.cgesgin.blogging_platform_api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgesgin.blogging_platform_api.model.entity.Category;
import com.cgesgin.blogging_platform_api.service.CategoryService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api")
@Tag(name = "Category", description = "API for category operations")
public class CategoryController {
    
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
     public List<Category> getAllCategories() {
         return categoryService.findAll();
     }

    @PostMapping("/categories")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        return ResponseEntity.ok(categoryService.save(category));
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        if (id == null || id <= 0)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(categoryService.findById(id));
    }


    @PutMapping("/categories/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        if (id == null || id <= 0)
            return ResponseEntity.badRequest().build();
        var categoryById = categoryService.findById(id);
        if (categoryById == null)
            return ResponseEntity.notFound().build();

        category.setId(id);
        return ResponseEntity.ok(categoryService.update(category));
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        if(id <= 0 ||categoryService.findById(id)==null) {
            return ResponseEntity.notFound().build();
        }
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
