package com.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ecommerce.entities.Order;
import com.ecommerce.entities.Role;
import com.ecommerce.entities.User;
import com.ecommerce.services.OrderService;
import com.ecommerce.services.UserService;

@RestController
@RequestMapping("/api/admin")
public class AdminOrderController {

    @Autowired 
    private OrderService orderService;

    @Autowired
    private UserService userService;

    // End-point to retrieve all orders placed by users for the admin-dashboard
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders(@RequestHeader("Authorization") String jwt) throws Exception {
        // Retrieve the admin user from the JWT token
        User adminUser = userService.findUserByJwtToken(jwt)
                                     .orElseThrow(() -> new Exception("User not authenticated"));

        // Ensure that the authenticated user is an admin
        if (!adminUser.getRole().equals(Role.ADMIN)) {
            throw new Exception("User is not authorized to perform this action");
        }

        // Retrieve all orders
        List<Order> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
    
    // End-point to update order status by admin for customer orders
    @PutMapping("/order/{id}/{orderStatus}")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long id,
                                                   @PathVariable String orderStatus,
                                                   @RequestHeader("Authorization") String jwt) throws Exception {
        // Retrieve the admin user from the JWT token
        User adminUser = userService.findUserByJwtToken(jwt)
                                     .orElseThrow(() -> new Exception("User not authenticated"));

        // Ensure that the authenticated user is an admin
        if (!adminUser.getRole().equals(Role.ADMIN)) {
            throw new Exception("User is not authorized to perform this action");
        }

        // Update order status
        Order updatedOrder = orderService.updateOrder(id, orderStatus);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }
}


