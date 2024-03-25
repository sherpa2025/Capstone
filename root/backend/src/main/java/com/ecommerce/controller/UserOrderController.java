package com.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dto.OrderRequest;
import com.ecommerce.entities.Order;
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
		//User user = userService.findUserByJwtToken(jwt);
		
		//Order order = orderService.createOrder(req, user.getId());
		//return new ResponseEntity<>(order, HttpStatus.OK);
		return null;
	}
	
	
	@GetMapping("/order/user")
	public ResponseEntity<List<Order>> getOrder(@RequestBody OrderRequest req,
												  @RequestHeader("Authorization") String jwt) throws Exception{
		//User user = userService.findUserByJwtToken(jwt);
		
		//List<Order> orders = orderService.getOrder(req, user.getId());
		//return new ResponseEntity<>(orders, HttpStatus.OK);
		return null;
	}
}
