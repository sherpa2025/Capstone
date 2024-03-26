package com.ecommerce.services.impl;

import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.ecommerce.dto.CreatePhotoRequest;
import com.ecommerce.entities.Category;
import com.ecommerce.entities.Photo;
import com.ecommerce.repositories.PhotoRepository;
import com.ecommerce.services.PhotoService;

@Service
public class PhotoServiceImpl implements PhotoService{

	@Autowired
	private PhotoRepository photoRepository;

	@Override
	public Photo createPhoto(CreatePhotoRequest req, Category category) {
		Photo photo = new Photo();
		photo.setPhotoCategory(category);
		photo.setName(req.getName());
		photo.setDescription(req.getDescription());
		photo.setImages(req.getImages());
		photo.setPrice(req.getPrice());
		photo.setCreationDate(req.getCreationDate());
		
		
		return photoRepository.save(photo);
	}

	@Override
	public void deletePhoto(Long photoId) throws Exception {
		Photo photo = findPhotoById(photoId);
	    // Delete the photo from the database
	    try {
	        photoRepository.delete(photo);
	    } catch (EmptyResultDataAccessException e) {
	        // If the photo doesn't exist, handle the exception
	        throw new Exception("Photo not found with ID: " + photoId);
	    }
		
	}
	
	@Override
	public List<Photo> getAllPhotos() {
	    return photoRepository.findAll();
	}

	@Override
	public List<Photo> getPhoto(String photoCategory) {
		List<Photo> photos = photoRepository.findAll();
		photos = filterByCategory(photos, photoCategory);
		return photos;
	}
	

	private List<Photo> filterByCategory(List<Photo> photos, String photoCategory) {
		
		return photos.stream().filter(photo -> {
			if(photo.getPhotoCategory()!= null) {
				return photo.getPhotoCategory().getName().equals(photoCategory);
			}
			return false;
		}).collect(Collectors.toList());	
	}

	@Override
	public Photo searchPhoto(String keyword) {
	    Optional<Photo> photoOptional = photoRepository.searchPhoto(keyword);
	    return photoOptional.orElse(null);
	}


	@Override
	public Photo findPhotoById(Long photoId) throws Exception {
		Optional<Photo> optionalPhoto = photoRepository.findById(photoId);
		
		if(optionalPhoto.isEmpty()) {
			throw new Exception("Photo don't exist...");
		}
		return optionalPhoto.get();
	}

	@Override
	public Photo updatePhotoAvailabilityStatus(Long photoId) throws Exception {
		Photo photo = findPhotoById(photoId);
		photo.setAvailable(!photo.isAvailable());
		return photoRepository.save(photo);
	}
	
}
