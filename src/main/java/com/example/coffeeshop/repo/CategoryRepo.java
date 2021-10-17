package com.example.coffeeshop.repo;

import com.example.coffeeshop.model.entity.CategoryEntity;
import com.example.coffeeshop.model.entity.enums.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<CategoryEntity, Long> {

    CategoryEntity findByName(CategoryEnum category);
}
