package com.congdinh.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.congdinh.dtos.category.CategoryDTO;
import com.congdinh.entities.Category;
import com.congdinh.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDTO> findAll() {
        var categories = categoryRepository.findAll();

        return categories.stream().map(category -> {
            var categoryDTO = new CategoryDTO();
            categoryDTO.setId(category.getId());
            categoryDTO.setName(category.getName());
            categoryDTO.setDescription(category.getDescription());

            return categoryDTO;
        }).toList();
    }

    @Override
    public CategoryDTO findById(int id) {
        var category = categoryRepository.findById(id);

        if (category == null) {
            return null;
        }

        var categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setDescription(category.getDescription());

        return categoryDTO;
    }

    @Override
    public void save(CategoryDTO categoryDTO) {
        if (categoryDTO == null) {
            throw new IllegalArgumentException("Category is required");
        }

        if (categoryDTO.getId() != 0) {
            var category = categoryRepository.findById(categoryDTO.getId());
            if (category == null) {
                throw new IllegalArgumentException("Category not found");
            }
            category.setName(categoryDTO.getName());
            category.setDescription(categoryDTO.getDescription());
            categoryRepository.save(category);
        } else {
            var category = new Category();
            category.setName(categoryDTO.getName());
            category.setDescription(categoryDTO.getDescription());
            categoryRepository.save(category);
        }
    }

    @Override
    public void update(CategoryDTO categoryDTO) {
        // Kiem tra gia tri can tao co null ko
        if (categoryDTO == null) {
            throw new IllegalArgumentException("Category is required");
        }

        // Kiem tra category co ton tai ko de update
        var category = categoryRepository.findById(categoryDTO.getId());

        // Neu ko ton tai thi throw exception
        if (category == null) {
            throw new IllegalArgumentException("Category not found");
        }

        // Neu ton tai thi update
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());

        // Goi ham update cua repository
        categoryRepository.update(category);
    }

    @Override
    public void delete(int id) {
        // Kiem tra category co ton tai ko de delete
        var category = categoryRepository.findById(id);

        // Neu ko ton tai thi throw exception
        if (category == null) {
            throw new IllegalArgumentException("Category not found");
        }

        // Goi ham delete cua repository
        categoryRepository.delete(id);
    }
}
