package com.ecommerce.dto;

import lombok.Data;

@Data
public class UpdateCartItemRequest {

	public Long cartItemId;
	private int quantity;
}
