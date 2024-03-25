package com.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.entities.Photo;
import com.ecommerce.services.PhotoService;
import com.ecommerce.services.UserService;

@RestController
@RequestMapping("/api/photo")
public class UserPhotoController {

	@Autowired
	private PhotoService photoService;
	

	@Autowired
	private UserService userService;
	
	
	@GetMapping("/search")
	public ResponseEntity<List<Photo>> searchPhoto(@RequestParam String name, 
											 @RequestHeader("Authorization") String jwt) throws Exception{
		
//		User user= userService.findUserByJwtToken(jwt);
		List<Photo> photos = photoService.searchPhoto(name);
		
		return new ResponseEntity<>(photos, HttpStatus.CREATED);
	}
}
