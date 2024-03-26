package com.ecommerce.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dto.CreatePhotoRequest;
import com.ecommerce.entities.Photo;
import com.ecommerce.entities.Role;
import com.ecommerce.entities.User;
import com.ecommerce.response.MessageResponse;
import com.ecommerce.services.PhotoService;
import com.ecommerce.services.UserService;

@RestController
@RequestMapping("/api/admin/photo")
public class AdminPhotoController {

    @Autowired
    private PhotoService photoService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Photo> createPhoto(@RequestBody CreatePhotoRequest req,
                                             @RequestHeader("Authorization") String jwt) throws Exception {

        // Retrieve the user from the JWT token
        User user = userService.findUserByJwtToken(jwt)
                                .orElseThrow(() -> new Exception("User not authenticated"));

        // Check if the user has ADMIN role
        if (!user.getRole().equals(Role.ADMIN)) {
            throw new Exception("User does not have permission to create photos");
        }

        // Create the photo
        Photo photo = photoService.createPhoto(req, req.getPhotoCategory());

        return new ResponseEntity<>(photo, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deletePhoto(@PathVariable Long id,
                                                       @RequestHeader("Authorization") String jwt) throws Exception {

        // Retrieve the user from the JWT token
        User user = userService.findUserByJwtToken(jwt)
                                .orElseThrow(() -> new Exception("User not authenticated"));

        // Check if the user has ADMIN role
        if (!user.getRole().equals(Role.ADMIN)) {
            throw new Exception("User does not have permission to delete photos");
        }

        // Delete the photo
        photoService.deletePhoto(id);

        MessageResponse res = new MessageResponse();
        res.setMessage("Photo deleted successfully");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Photo> updatePhotoAvailabilityStatus(@PathVariable Long id,
                                                               @RequestHeader("Authorization") String jwt) throws Exception {

        // Retrieve the user from the JWT token
        User user = userService.findUserByJwtToken(jwt)
                                .orElseThrow(() -> new Exception("User not authenticated"));

        // Check if the user has ADMIN role
        if (!user.getRole().equals(Role.ADMIN)) {
            throw new Exception("User does not have permission to update photo availability status");
        }

        // Update the photo availability status
        Photo photo = photoService.updatePhotoAvailabilityStatus(id);

        return new ResponseEntity<>(photo, HttpStatus.OK);
    }

    // Other endpoints...
}

