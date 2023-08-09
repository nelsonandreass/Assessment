package com.example.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.main.models.Category;

public interface CategoryRepository extends JpaRepository<Category, String>{
	
	 public Category findByid(String id);
}
