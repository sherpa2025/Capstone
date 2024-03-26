package com.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dto.OrderRequest;
import com.ecommerce.entities.Order;
import com.ecommerce.entities.User;
import com.ecommerce.services.OrderService;
import com.ecommerce.services.UserService;

@RestController
@RequestMapping("/api")
public class UserOrderController {

	@Autowired 
	private OrderService orderService;
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/order/add")
	public ResponseEntity<Order> createOrder(@RequestBody OrderRequest req,
												  @RequestHeader("Authorization") String jwt) throws Exception{
        // Retrieve the user from the JWT token
        User user = userService.findUserByJwtToken(jwt)
                                .orElseThrow(() -> new Exception("User not authenticated"));
		
		Order order = orderService.createOrder(req, user);
		return new ResponseEntity<>(order, HttpStatus.OK);
	}
	
	
	@GetMapping("/order/user")
	public ResponseEntity<List<Order>> getOrder(@RequestHeader("Authorization") String jwt) throws Exception{
        // Retrieve the user from the JWT token
        User user = userService.findUserByJwtToken(jwt)
                                .orElseThrow(() -> new Exception("User not authenticated"));
        
		List<Order> orders = orderService.getUsersOrder(user.getId());
		return new ResponseEntity<>(orders, HttpStatus.OK);

	}
}
