package com.example.coffeeshop.service;

import com.example.coffeeshop.model.entity.CategoryEntity;
import com.example.coffeeshop.model.entity.enums.CategoryEnum;

public interface CategoryService {
    CategoryEntity findByName(CategoryEnum category);
}
