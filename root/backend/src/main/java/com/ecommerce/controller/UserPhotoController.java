package com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.entities.Photo;
import com.ecommerce.services.PhotoService;


@RestController
@RequestMapping("/api/photo")
public class UserPhotoController {

	@Autowired
	private PhotoService photoService;
	
	
	
	@GetMapping("/{name}")
    public ResponseEntity<Photo> getPhotoByName(@PathVariable String name) {
        // Retrieve the photo by name
        Photo photo = photoService.searchPhoto(name);

        if (photo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(photo, HttpStatus.OK);
    }
}
