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
@WebMvcTest({CategoryController.class, EmployeeController.class})
public class EmployeeControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper mapper = new ObjectMapper();
	
	@MockBean
	private EmployeeServices employeeService; 
	
	@MockBean
	private CategoryService catService;
	
	@Test
	public void testGetSuccess() {
		 List<Employee> employees = new ArrayList<>();
	        employees.add(new Employee("John Doe",1,new BigInteger(String.valueOf(2000000))));
	        employees.add(new Employee("Juan",2,new BigInteger(String.valueOf(1000000))));

	        Mockito.when(employeeService.findAll()).thenReturn(employees);

	        try {
				mockMvc.perform(get("/employee")).andExpect(status().isOk());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	              
	}
	
	@Test
	public void testGetFailed() {
		 List<Employee> employees = new ArrayList<>();
	        employees.add(new Employee("John Doe",1,new BigInteger(String.valueOf(2000000))));
	        employees.add(new Employee("Juan",2,new BigInteger(String.valueOf(1000000))));

	        Mockito.when(employeeService.findAll()).thenReturn(employees);

	        try {
				mockMvc.perform(post("/employee")).andExpect(status().isMethodNotAllowed());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    
	}
	
	
	
	@Test
	public void testAddEmployeeSuccess() {
		Category cat = new Category(1, "Manager", (float) 0.1);
		when(catService.findByCategoryCode(1)).thenReturn(cat);
		 
		Employee emp = new Employee("Nelson", 1, new BigInteger(String.valueOf(10000)));
		String requestEmp = null;
		try {
			requestEmp = mapper.writeValueAsString(emp);
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			mockMvc.perform(post("/employee/save").contentType("application/json").content(requestEmp))
			.andExpect(status().isOk());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
		
	}
	
	@Test
	public void testAddEmployeeFailed() {
		Category cat = new Category(1, "Manager", (float) 0.1);
		when(catService.findByCategoryCode(1)).thenReturn(cat);
		Employee emp = new Employee("Nelson", 11, new BigInteger(String.valueOf(10000)));
		
		String request = null;
		try {
			request = mapper.writeValueAsString(emp);
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			mockMvc.perform(post("/employee/save").contentType("application/json").content(request))
			.andExpect(status().isNotFound());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
	}
	
}
