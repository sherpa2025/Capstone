package com.ecommerce.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.dto.AddCartItemRequest;
import com.ecommerce.entities.Cart;
import com.ecommerce.entities.CartItem;
import com.ecommerce.entities.Photo;
import com.ecommerce.entities.User;
import com.ecommerce.repositories.CartItemRepository;
import com.ecommerce.repositories.CartRepository;
import com.ecommerce.services.CartService;
import com.ecommerce.services.PhotoService;
import com.ecommerce.services.UserService;

@Service
public class CartServiceImpl implements CartService{

	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private PhotoService photoService;
	
	@Autowired
	private UserService userService;
	
	
	
	@Override
	public CartItem addItemToCart(AddCartItemRequest req, String jwt) throws Exception {
//		User user = userService.findUserByJwt(jwt);
		
		Photo photo = photoService.findPhotoById(req.getPhotoId());
//		Cart cart = cartRepository.findByCustomerId(user.getId());
//		
//		for(CartItem cartItem : cart.getItems()) {
//			if(cartItem.getPhoto().equals(photo)) {
//				int newQuantity = cartItem.getQuantity()+req.getQuantity();
//				return updateCartItemQuantity(cartItem.getId(), newQuantity);
//			}
//		}
		
		CartItem newCartItem = new CartItem();
		newCartItem.setPhoto(photo);
//		newCartItem.setCart(cart);
		newCartItem.setQuantity(req.getQuantity());
		newCartItem.setTotalPrice(req.getQuantity()*photo.getPrice());
		
		CartItem savedCartItem = cartItemRepository.save(newCartItem); 
//		cart.getItems().add(savedCartItem);
		
		return savedCartItem;
	}

	@Override
	public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception {
		Optional<CartItem> optionalCartItem =cartItemRepository.findById(cartItemId);
		
		if(optionalCartItem.isEmpty()) {
			throw new Exception("Cart Item is empty");
		}
		CartItem item = optionalCartItem.get();
		item.setQuantity(quantity);
		item.setTotalPrice(item.getPhoto().getPrice()*quantity);
		return cartItemRepository.save(item);
	}

	@Override
	public Cart removeCartItem(Long cartItemId, String jwt) throws Exception {
//		User user = userService.findUserByJwt(jwt);
		
//		Cart cart = cartRepository.findByCustomerId(user.getId());

		Optional<CartItem> optionalCartItem =cartItemRepository.findById(cartItemId);
		
		if(optionalCartItem.isEmpty()) {
			throw new Exception("Cart Item is empty");
		}
		
		CartItem item = optionalCartItem.get();
//		cart.getItems.remove(item);
//		return cartRepository.save(cart);
		return null;
	}

	@Override
	public Double calculateTotal(Cart cart) throws Exception {
		Double total = 0D;
		for(CartItem cartItem : cart.getItem()) {
			total += cartItem.getPhoto().getPrice()*cartItem.getQuantity();
		}
		return total;
	}

	@Override
	public Cart findCartById(Long id) throws Exception {
		Optional<Cart> optionalCart =cartRepository.findById(id);
		
		if(optionalCart.isEmpty()) {
			throw new Exception("Cart not found with id "+ id);
		}
		
		return optionalCart.get();
	}

	@Override
	public Cart findCartByUserId(Long userId) throws Exception {
	Cart cart = cartRepository.findByCustomerId(userId);
	cart.setTotal(calculateTotal(cart));
	return cart;
		
//		User user = userService.findUserByJwtToken(jwt);
//		return cartRepository.findByCustomerId(user.getId());
		//return null;
	}

	@Override
	public Cart clearCart(Long userId) throws Exception {
//		User user = userService.findUserByJwtToken(jwt);
		Cart cart= findCartById(userId);
//		Cart cart= findCartById(user.getId());
		cart.getItem().clear();
		return cartRepository.save(cart);
		
	}

}
