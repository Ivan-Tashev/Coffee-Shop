package com.example.coffeeshop.service.impl;

import com.example.coffeeshop.model.entity.CategoryEntity;
import com.example.coffeeshop.model.entity.enums.CategoryEnum;
import com.example.coffeeshop.repo.CategoryRepo;
import com.example.coffeeshop.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo categoryRepo;

    public CategoryServiceImpl(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public CategoryEntity findByName(CategoryEnum category) {
        return categoryRepo.findByName(category);
    }
}
