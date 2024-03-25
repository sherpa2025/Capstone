package com.ecommerce.dto;

import lombok.Data;

@Data
public class AddCartItemRequest {

	private Long photoId;
	
	private int quantity;
	
}
