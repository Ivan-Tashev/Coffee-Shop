package com.example.coffeeshop.model.entity;

import com.example.coffeeshop.model.entity.enums.CategoryEnum;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class CategoryEntity extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private CategoryEnum name;
    @Column(name = "needed_time", nullable = false)
    private Integer neededTime;

    public CategoryEnum getName() {
        return name;
    }

    public CategoryEntity setName(CategoryEnum name) {
        this.name = name;
        return this;
    }

    public Integer getNeededTime() {
        return neededTime;
    }

    public CategoryEntity setNeededTime() {
        this.neededTime = this.name.getMin();
        return this;
    }
}
