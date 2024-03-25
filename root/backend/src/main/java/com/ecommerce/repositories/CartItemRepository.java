package com.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.entities.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}
