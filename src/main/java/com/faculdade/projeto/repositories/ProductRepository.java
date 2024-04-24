package com.faculdade.projeto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.faculdade.projeto.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
}
