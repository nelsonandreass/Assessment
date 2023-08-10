package com.example.main.controllers;

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.example.main.models.Category;
import com.example.main.models.Employee;
import com.example.main.services.CategoryService;
import com.example.main.services.EmployeeServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@ExtendWith(SpringExtension.class)
@WebMvcTest({CategoryController.class})
public class CategoryControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper mapper = new ObjectMapper();
	
	@MockBean
	private CategoryService catService; 
	
	@Test
	public void testGetCategorySuccess() {
		 List<Category> cat = new ArrayList<>();
	        cat.add(new Category(1, "Manager", (float) 0.1));
	        cat.add(new Category(2, "Supervisor", (float) 0.06));

	        Mockito.when(catService.findAll()).thenReturn(cat);

	        try {
				mockMvc.perform(get("/category")).andExpect(status().isOk());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    
	}
	
	@Test
	public void testGetCategoryFailedMethodnotAllowed() {
		 List<Category> cat = new ArrayList<>();
	        cat.add(new Category(1, "Manager", (float) 0.1));
	        cat.add(new Category(2, "Supervisor", (float) 0.06));

	        Mockito.when(catService.findAll()).thenReturn(cat);

	        try {
				mockMvc.perform(post("/category")).andExpect(status().isMethodNotAllowed());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    
	}
	
	@Test
	public void testAddCategorySucces() {
		 Category cat = new Category(1, "Manager", (float) 0.1);

		 doNothing().when(catService).saveOne(cat);
	        try {
				mockMvc.perform(post("/category/save")
				    .contentType("application/json")
				    .content(mapper.writeValueAsString(cat)))
				    .andExpect(status().isOk())
				    .andExpect(jsonPath("$.success").value(true));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	@Test
	public void testAddCategoryFailedMethodNotAllowed() {
		 Category cat = new Category(1, "Manager", (float) 0.1);
		 doNothing().when(catService).saveOne(cat);

	        try {
				mockMvc.perform(get("/category/save")
				    .contentType("application/json")
				    .content(mapper.writeValueAsString(cat)))
				    .andExpect(status().isMethodNotAllowed());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
}
