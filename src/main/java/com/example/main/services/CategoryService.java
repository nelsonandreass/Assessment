package com.example.main.services;

import java.util.List;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.main.models.Category;
import com.example.main.repositories.CategoryRepository;

@Service
@Transactional
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	
	public void saveOne(Category cat) {
		Category category= categoryRepo.save(cat);
		
	}
	
	public List<Category> findAll(){
        List<Category> listCategory= categoryRepo.findAll();
        return listCategory;
    }
	
	public Category findByCategoryCode(int categoryCode) {
		Category cat = categoryRepo.findByCategoryCode(categoryCode);
		return cat;
	}

	
}
