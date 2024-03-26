package com.ecommerce.services;

import java.util.List;

import com.ecommerce.entities.Category;

public interface CategoryService {

	public Category createCategory(String name)throws Exception;
	
	public Category findCategoryById(Long Id) throws Exception;

	List<Category> getAllCategories();
	
}
