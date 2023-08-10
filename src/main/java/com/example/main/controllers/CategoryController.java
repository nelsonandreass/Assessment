package com.example.main.controllers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.main.models.Category;
import com.example.main.models.Employee;
//import com.sgo.hsbcproject.models.Users;
//import com.sgo.hsbcproject.models.pojo.AjaxResponseBody;
//import com.sgo.hsbcproject.services.EmployeeServices;
import com.example.main.models.pojo.AjaxResponseBody;
import com.example.main.services.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class CategoryController {
	
	@Autowired
	private ObjectMapper mapper = new ObjectMapper();
	@Autowired
	private CategoryService catService = new CategoryService();
	
	private AjaxResponseBody response = new AjaxResponseBody();	
	
	private HttpStatus status;
	private void setResponse(Object obj, String status , boolean success) {
		response.setResult(obj);
		response.setMsg(status);
		response.setSuccess(success);
	}
	
	@PostMapping(value = "/category/save")
	public ResponseEntity<AjaxResponseBody> save(@RequestBody LinkedHashMap<String,Object> dataJson){
		Category cat= mapper.convertValue(dataJson, Category.class);
		Category check = catService.findByCategoryCode((int)dataJson.get("categoryCode"));
		if(check == null) {
			catService.saveOne(cat);
			setResponse(cat, "Success", true);
			status = HttpStatus.OK;
		}else {
			setResponse(new ArrayList<Employee>(), "Data has been registered", false);
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<AjaxResponseBody>(response,status);
	}
	
	@GetMapping(value = "/category")
	public ResponseEntity<AjaxResponseBody> findAll() {
		List<Category> listCat= catService.findAll();
		setResponse(listCat, "Success", true);
		status = HttpStatus.OK;
		return new ResponseEntity<AjaxResponseBody>(response , status);
	}
	
	@PostMapping(value = "/category/update")
	public ResponseEntity<AjaxResponseBody> update(@RequestBody LinkedHashMap<String,Object> dataJson){
		Category cat = catService.findByCategoryCode(Integer.parseInt(dataJson.get("categoryCode").toString()));
		cat.setBonus(Float.parseFloat(dataJson.get("bonus").toString()));
		cat.setCategoryDescription(dataJson.get("categoryDescription").toString());
		catService.saveOne(cat);
		setResponse(cat, "Success", true);
		status = HttpStatus.OK;
		return new ResponseEntity<AjaxResponseBody>(response , status);
	}
	
}
