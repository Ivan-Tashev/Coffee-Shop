package com.example.coffeeshop;

import com.example.coffeeshop.model.entity.CategoryEntity;
import com.example.coffeeshop.model.entity.enums.CategoryEnum;
import com.example.coffeeshop.repo.CategoryRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AppDbInit implements CommandLineRunner {
    private final CategoryRepo categoryRepo;

    public AppDbInit(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        if (categoryRepo.count() == 0) {
            CategoryEntity categoryEntityDrink = new CategoryEntity().setName(CategoryEnum.DRINK).setNeededTime();
            CategoryEntity categoryEntityCoffee = new CategoryEntity().setName(CategoryEnum.COFFEE).setNeededTime();
            CategoryEntity categoryEntityCake = new CategoryEntity().setName(CategoryEnum.CAKE).setNeededTime();
            CategoryEntity categoryEntityOther = new CategoryEntity().setName(CategoryEnum.OTHER).setNeededTime();

            categoryRepo.saveAll(List.of(categoryEntityDrink, categoryEntityCoffee,
                    categoryEntityCake, categoryEntityOther));
        }
    }
}
