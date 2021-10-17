package com.example.coffeeshop.repo;

import com.example.coffeeshop.model.entity.OrderEntity;
import com.example.coffeeshop.model.view.OrderViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<OrderEntity, Long> {

//    @Query("SELECT o.name, o.price, o.categoryEntity.name, o.employee FROM OrderEntity o")
    List<OrderEntity> findAll();
}
