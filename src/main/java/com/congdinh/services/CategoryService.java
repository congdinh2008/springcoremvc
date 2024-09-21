package com.congdinh.services;

import java.util.List;

import com.congdinh.dtos.category.CategoryDTO;

public interface CategoryService {
    List<CategoryDTO> findAll();

    CategoryDTO findById(int id);

    void save(CategoryDTO category);

    void update(CategoryDTO category);

    void delete(int id);
}
