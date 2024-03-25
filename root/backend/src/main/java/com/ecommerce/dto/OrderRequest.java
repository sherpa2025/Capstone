package com.ecommerce.dto;

import com.ecommerce.entities.Address;

import lombok.Data;

@Data
public class OrderRequest {

	private Address deliveryAddress;
}
