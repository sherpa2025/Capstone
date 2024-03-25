package com.ecommerce.services;

import java.util.List;

import com.ecommerce.dto.OrderRequest;
import com.ecommerce.entities.Order;
import com.ecommerce.entities.User;

public interface OrderService {

	public Order createOrder(OrderRequest order, User user) throws Exception;
	
	public Order updateOrder(Long orderId, String orderStatus) throws Exception;
	
	public void cancelOrder(Long orderId) throws Exception;
	
	public List<Order> getUsersOrder(Long userId) throws Exception;
	
	public Order findOrderById(Long orderId) throws Exception;
}
