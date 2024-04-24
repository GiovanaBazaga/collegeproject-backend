package com.faculdade.projeto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.faculdade.projeto.repositories.UserRepository;

@Service
public class AuthorizationService implements UserDetailsService{

	@Autowired
	UserRepository repository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return repository.findByEmail(username);
	}

}

