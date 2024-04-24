package com.faculdade.projeto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.faculdade.projeto.entities.User;
import com.faculdade.projeto.records.AuthenticationDTO;
import com.faculdade.projeto.records.LoginResponseDTO;
import com.faculdade.projeto.records.RegisterDTO;
import com.faculdade.projeto.repositories.UserRepository;
import com.faculdade.projeto.services.TokenService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("auth")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired 
	private UserRepository repository;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data){
		var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
		var auth = this.authenticationManager.authenticate(usernamePassword);
		
		var token = tokenService.generateToken((User)auth.getPrincipal());
		
		return ResponseEntity.ok(new LoginResponseDTO(token));
	}
	
	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody @Valid RegisterDTO data) {
		if(this.repository.findByEmail(data.email()) != null) return ResponseEntity.badRequest().build();
		
		String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
		User newUser = new User(data.name(), data.email(), data.phone(), encryptedPassword, data.role());
		
		this.repository.save(newUser);
		
		return ResponseEntity.ok().build();
	}
}
