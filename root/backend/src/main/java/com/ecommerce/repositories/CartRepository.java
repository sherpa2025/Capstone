package com.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.entities.Cart;


public interface CartRepository extends JpaRepository<Cart, Long> {

	public Cart findByCustomerId(Long userId);
}
