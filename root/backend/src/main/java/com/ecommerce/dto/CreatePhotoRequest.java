package com.ecommerce.dto;

import java.util.Date;
import java.util.List;

import com.ecommerce.entities.Category;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class CreatePhotoRequest {

private String name;
	
	private String description;
	
	private Double price;
	
	@ManyToOne
	private Category photoCategory;
	
	@Column(length = 1000)
	@ElementCollection
	private List<String> images;
	
	private boolean available;
	
	private Date creationDate;
}
