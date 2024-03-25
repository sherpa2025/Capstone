package com.ecommerce.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.entities.Category;
import com.ecommerce.repositories.CategoryRepository;
import com.ecommerce.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public Category createCategory(String name) throws Exception{
		Category category = new Category();
		category.setName(name);
		return categoryRepository.save(category);
	}

	@Override
	public Category findCategoryById(Long id) throws Exception {
		Optional<Category> optionalCategory = categoryRepository.findById(id);
		if(optionalCategory.isEmpty()) {
			throw new Exception("Photo don't exist...");
		}
		return optionalCategory.get();
	}

}
