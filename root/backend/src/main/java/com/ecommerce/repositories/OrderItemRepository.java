package com.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
