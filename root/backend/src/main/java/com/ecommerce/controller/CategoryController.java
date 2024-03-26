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

import com.ecommerce.entities.Category;
import com.ecommerce.entities.Role;
import com.ecommerce.entities.User;
import com.ecommerce.services.CategoryService;
import com.ecommerce.services.UserService;

@RestController
@RequestMapping("/api")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private UserService userService;
	

	@PostMapping("/admin/category")
    public ResponseEntity<Category> createCategoryByAdmin(@RequestBody Category category, 
                                                           @RequestHeader("Authorization") String jwt) throws Exception {
		 // Retrieve the user from the JWT token
        User user = userService.findUserByJwtToken(jwt)
                               .orElseThrow(() -> new Exception("User not authenticated"));

        // Check if the user has ADMIN role
        if (!user.getRole().equals(Role.ADMIN)) {
            throw new Exception("User does not have permission to create categories");
        }

        // Create the category
        Category createdCategory = categoryService.createCategory(category.getName());

        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

	
	@GetMapping("/category")
	public ResponseEntity<List<Category>> getCategory(@RequestBody Category category){
		
		List<Category> createCategory = categoryService.getAllCategories();
		
		return new ResponseEntity<>(createCategory, HttpStatus.CREATED);
	}
	
}
