package com.example.coffeeshop.model.binding;

import com.example.coffeeshop.model.entity.enums.CategoryEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderAddBindModel {
    @NotNull
    @Size(min = 3, max = 20)
    private String name;
    @NotNull
    @Positive
    private BigDecimal price;
    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime orderTime;
    @NotNull
    private CategoryEnum category;
    @NotNull
    @Size(min = 5)
    private String description;

    public String getName() {
        return name;
    }

    public OrderAddBindModel setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OrderAddBindModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public OrderAddBindModel setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
        return this;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public OrderAddBindModel setCategory(CategoryEnum category) {
        this.category = category;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OrderAddBindModel setDescription(String description) {
        this.description = description;
        return this;
    }
}
