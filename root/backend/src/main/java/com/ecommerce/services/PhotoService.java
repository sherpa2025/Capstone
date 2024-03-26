package com.ecommerce.services;

import java.util.List;

import com.ecommerce.dto.CreatePhotoRequest;
import com.ecommerce.entities.Category;
import com.ecommerce.entities.Photo;

public interface PhotoService {

	public Photo createPhoto(CreatePhotoRequest req, Category category);
	
	void deletePhoto(Long photoId) throws Exception;
	
	public List<Photo> getPhoto(String photoCategory);
	
	public Photo searchPhoto(String keyword);
	
	public Photo findPhotoById(Long photoId) throws Exception;
	
	public Photo updatePhotoAvailabilityStatus(Long photoId) throws Exception;

	List<Photo> getAllPhotos();
}
