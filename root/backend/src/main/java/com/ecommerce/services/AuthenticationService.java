package com.ecommerce.services;

import com.ecommerce.dto.JwtAuthenticationResponse;
import com.ecommerce.dto.RefreshTokenRequest;
import com.ecommerce.dto.SignInRequest;
import com.ecommerce.dto.SignUpRequest;
import com.ecommerce.entities.User;

public interface AuthenticationService {

	User signup(SignUpRequest signUpRequest) throws Exception;
	
	JwtAuthenticationResponse signin(SignInRequest signinRequest);
	
	JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
