package com.example.coffeeshop.model.view;

import com.example.coffeeshop.model.entity.enums.CategoryEnum;
import com.example.coffeeshop.model.service.UserServiceModel;

import java.math.BigDecimal;

public class OrderViewModel {
    private Long id;
    private String name;
    private BigDecimal price;
    private CategoryEnum category;
    private UserServiceModel employee;

    public Long getId() {
        return id;
    }

    public OrderViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public OrderViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OrderViewModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public OrderViewModel setCategory(CategoryEnum category) {
        this.category = category;
        return this;
    }

    public UserServiceModel getEmployee() {
        return employee;
    }

    public OrderViewModel setEmployee(UserServiceModel employee) {
        this.employee = employee;
        return this;
    }
}
