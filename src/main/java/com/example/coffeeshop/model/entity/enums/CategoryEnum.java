package com.example.coffeeshop.model.entity.enums;

public enum CategoryEnum {
    DRINK(1),
    COFFEE(2),
    CAKE(10),
    OTHER(5);

    private int min;

    CategoryEnum(int min) {
        this.min = min;
    }

    public int getMin() {
        return min;
    }
}
