package com.congdinh.repositories;

import java.util.List;

import com.congdinh.entities.Category;

public interface CategoryRepository {
    List<Category> findAll();

    Category findById(int id);

    void save(Category category);

    void update(Category category);

    void delete(int id);
}
