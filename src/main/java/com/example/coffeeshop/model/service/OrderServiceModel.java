package com.example.coffeeshop.model.service;

import com.example.coffeeshop.model.entity.CategoryEntity;
import com.example.coffeeshop.model.entity.UserEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderServiceModel {
    private Long id;
    private String name;
    private BigDecimal price;
    private LocalDateTime orderTime;
    private CategoryEntity category;
    private String description;
    private UserEntity employee;

    public Long getId() {
        return id;
    }

    public OrderServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public OrderServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OrderServiceModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public OrderServiceModel setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
        return this;
    }

    public CategoryEntity getCategoryEntity() {
        return category;
    }

    public OrderServiceModel setCategoryEntity(CategoryEntity category) {
        this.category = category;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OrderServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public UserEntity getEmployee() {
        return employee;
    }

    public OrderServiceModel setEmployee(UserEntity employee) {
        this.employee = employee;
        return this;
    }
}
