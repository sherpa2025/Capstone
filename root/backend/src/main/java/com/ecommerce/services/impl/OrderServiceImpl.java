package com.ecommerce.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.dto.OrderRequest;
import com.ecommerce.entities.Address;
import com.ecommerce.entities.Cart;
import com.ecommerce.entities.CartItem;
import com.ecommerce.entities.Order;
import com.ecommerce.entities.OrderItem;
import com.ecommerce.entities.User;
import com.ecommerce.repositories.AddressRepository;
import com.ecommerce.repositories.OrderItemRepository;
import com.ecommerce.repositories.OrderRepository;
import com.ecommerce.repositories.UserRepository;
import com.ecommerce.services.CartService;
import com.ecommerce.services.OrderService;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CartService cartService;
	
	@Override
	public Order createOrder(OrderRequest order, User user) throws Exception {

		Address shipAddress = order.getDeliveryAddress();
		Address savedAddress= addressRepository.save(shipAddress);
		
		if(!user.getAddresses().contains(savedAddress)) {
			user.getAddresses().add(savedAddress);
			userRepository.save(user);
		}
		
		Order createdOrder=new Order();
		createdOrder.setCustomer(user);
		createdOrder.setCreationDate(new Date());
		createdOrder.setOrderStatus("PENDING");
		createdOrder.setDeliveryAddress(savedAddress);
		
		Cart cart= cartService.findCartByUserId(user.getId());
		
		List<OrderItem> orderItems= new ArrayList<>();
		
		for(CartItem cartItem: cart.getItem()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setPhoto(cartItem.getPhoto());
			orderItem.setQuantity(cartItem.getQuantity());
			orderItem.setTotalPrice(cartItem.getTotalPrice());
			
			OrderItem savedOrderItem = orderItemRepository.save(orderItem);
			orderItems.add(savedOrderItem);
		}
		Double totalPrice = cartService.calculateTotal(cart);
		
		createdOrder.setItems(orderItems);
		createdOrder.setTotalPrice(totalPrice);
		
		Order savedOrder= orderRepository.save(createdOrder);
		return createdOrder; 
		
		
	}

	@Override
	public Order updateOrder(Long orderId, String orderStatus) throws Exception {
		Order order=findOrderById(orderId);
		if(orderStatus.equals("PENDING") 
				|| orderStatus.equals("SHIPPED")
				|| orderStatus.equals("DELIVERED")) {
			order.setOrderStatus(orderStatus);
			return orderRepository.save(order);
		}
		throw new Exception("Please select valid order status");
	}

	@Override
	public void cancelOrder(Long orderId) throws Exception {
		Order order = findOrderById(orderId);
		orderRepository.deleteById(orderId);
		
	}

	@Override
	public List<Order> getUsersOrder(Long userId) throws Exception {
		
		return orderRepository.findByCustomerId(userId);
	}

	@Override
	public Order findOrderById(Long orderId) throws Exception {
		Optional<Order> optionalOrder = orderRepository.findById(orderId);
		if(optionalOrder.isEmpty()) {
			throw new Exception("Order not found");
		}
		return optionalOrder.get();
	}

}
