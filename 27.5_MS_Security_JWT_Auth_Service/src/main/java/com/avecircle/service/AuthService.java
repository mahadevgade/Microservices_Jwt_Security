package com.avecircle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.avecircle.entity.UserCredential;
import com.avecircle.repo.AuthRepository;

@Service
public class AuthService {
	
	@Autowired
	private AuthRepository repository;
	
	@Autowired
	private PasswordEncoder pwdEncoder;
	
	@Autowired
	private JwtService jwt;
	
	public String saveUsers(UserCredential credential)
	{
		credential.setPassword(pwdEncoder.encode(credential.getPassword()));
		repository.save(credential);
		return "user added to the DB...";
	}

	public String genrateToken(String username)
	{
		return jwt.generateToken(username);		
	}
	
	public void validateToken(String token)
	{
		jwt.validateToken(token);
	}
}
