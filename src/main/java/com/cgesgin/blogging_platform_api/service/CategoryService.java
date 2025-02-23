package com.cgesgin.blogging_platform_api.service;

import org.springframework.stereotype.Service;

import com.cgesgin.blogging_platform_api.model.entity.Category;
import com.cgesgin.blogging_platform_api.model.service.IGenericService;
import com.cgesgin.blogging_platform_api.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService implements IGenericService<Category> {

    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
  
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(Long id) {
        var category = categoryRepository.findById(id).orElse(null);
        if (category == null) {
            return null;
        }
        return category; 
    }

    public Category save(Category category) {
        if (category.getId() == null || category.getId() > 0)
            category.setId(null);
        if (category.getPosts() == null)
            category.setPosts(new ArrayList<>());
        return categoryRepository.save(category);
    }

    public Category update(Category updatedCategory) {
        Category category = categoryRepository.findById(updatedCategory.getId())
                .orElse(null);

        if (category == null)
            return null;

        category.setName(updatedCategory.getName());
        return categoryRepository.save(category);
    }

    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

}
