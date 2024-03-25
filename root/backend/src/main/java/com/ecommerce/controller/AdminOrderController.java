package com.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dto.OrderRequest;
import com.ecommerce.entities.Order;
import com.ecommerce.services.OrderService;
import com.ecommerce.services.UserService;

@RestController
@RequestMapping("/api/admin")
public class AdminOrderController {

	@Autowired 
	private OrderService orderService;
	
	@Autowired
	private UserService userService;
	
	//Have admin see the order
//	@GetMapping("/order")
//	public ResponseEntity<List<Order>> getOrder(@Path OrderRequest req,
//												  @RequestHeader("Authorization") String jwt) throws Exception{
//		//User user = userService.findUserByJwtToken(jwt);
//		
//		//List<Order> orders = orderService.createOrder(req, user.getId());
//		//return new ResponseEntity<>(orders, HttpStatus.OK);
//		return null;
//	}
	
	@PutMapping("/order/{id}/{orderStatus}")
	public ResponseEntity<Order> updateOrderStatus(@PathVariable Long id,
			  @RequestHeader("Authorization") String jwt) throws Exception{
	//User user = userService.findUserByJwtToken(jwt);
	
	//Order order = orderService.updateOrderStatus(id, orderStatus);
	//return new ResponseEntity<>(order, HttpStatus.OK);
	return null;
	}
}

