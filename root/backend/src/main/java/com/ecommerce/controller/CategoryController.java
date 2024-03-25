package com.ecommerce.controller;

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
import com.ecommerce.services.CategoryService;

@RestController
@RequestMapping("/api")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	

	@PostMapping("/admin/category")
	public ResponseEntity<Category> createCategory(@RequestBody Category category, 
											 @RequestHeader("Authorization") String jwt)throws Exception{
		
//		User user= userService.findUserByJwtToken(jwt);
		Category createCategory = categoryService.createCategory(category.getName());
		
		return new ResponseEntity<>(createCategory, HttpStatus.CREATED);
	}
	
//	@GetMapping
//	public ResponseEntity<Category> createCategory(@RequestBody Category category, 
//											 @RequestHeader("Authorization") String jwt)throws Exception{
//		
////		User user= userService.findUserByJwtToken(jwt);
//		Category createCategory = categoryService.createCategory(category.getName());
//		
//		return new ResponseEntity<>(createCategory, HttpStatus.CREATED);
//	}
	
}
