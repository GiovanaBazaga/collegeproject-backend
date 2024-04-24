package com.faculdade.projeto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.faculdade.projeto.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	
}
