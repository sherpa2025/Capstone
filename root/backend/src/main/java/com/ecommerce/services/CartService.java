package com.ecommerce.services;

import com.ecommerce.dto.AddCartItemRequest;
import com.ecommerce.entities.Cart;
import com.ecommerce.entities.CartItem;

public interface CartService {

	public CartItem addItemToCart(AddCartItemRequest req, String jwt) throws Exception;
	
	public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception;
	
	public Cart removeCartItem (Long cartItemId, String jwt)throws Exception;
	
	public Double calculateTotal(Cart cart) throws Exception;
	
	public Cart findCartById(Long id) throws Exception;
	
	public Cart findCartByUserId(Long userId) throws Exception;
	//public Cart findCartByUserId(String jwt) throws Exception;
//	public Cart clearCart(String jwt) throws Exception;
	public Cart clearCart(Long userId) throws Exception;
	
}
