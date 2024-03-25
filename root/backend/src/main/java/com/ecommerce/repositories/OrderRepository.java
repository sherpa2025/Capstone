package com.ecommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	public List<Order> findByCustomerId(Long userId);
}
