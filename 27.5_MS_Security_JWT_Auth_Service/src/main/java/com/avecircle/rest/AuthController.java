package com.avecircle.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.avecircle.binding.AuthRequest;
import com.avecircle.binding.AuthResponse;
import com.avecircle.entity.UserCredential;
import com.avecircle.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthService service;
	
	@Autowired
	private AuthenticationManager authManager;
	
	
	
	@PostMapping("/register")
	public String addUserData(@RequestBody UserCredential credential)
	{
		return service.saveUsers(credential);
		
	}
		
	
	@PostMapping("/login")
	public AuthResponse getToken(@RequestBody AuthRequest authRequest) {

		AuthResponse authResponse = new AuthResponse();

		try {
			
			UsernamePasswordAuthenticationToken authenticationToken = 
					new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword());
		     
			Authentication authenticate = authManager.authenticate(authenticationToken);
					
			String token = null;

			if (authenticate.isAuthenticated()) {
				token = service.genrateToken(authRequest.getUsername());
				authResponse.setToken(token);
				authResponse.setLoginValid("yes");
			}
		} catch (Exception e) {
			//e.printStackTrace();
			
			  authResponse.setToken("");
			  authResponse.setLoginValid("No");			 
		}

		return authResponse;
	}


	@GetMapping("/validate/{token}")
	public String validateToken(@RequestParam("token") String token) {
		service.validateToken(token);
		return "Token is valid";
	}
}
