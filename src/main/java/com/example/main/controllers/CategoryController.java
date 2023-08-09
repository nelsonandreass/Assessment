package com.example.main.controllers;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.main.models.Category;
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
	
	@PostMapping(value = "/category/save")
	public ResponseEntity<AjaxResponseBody> save(@RequestBody LinkedHashMap<String,Object> dataJson){
		Category cat= mapper.convertValue(dataJson, Category.class);
		catService.saveOne(cat);
		AjaxResponseBody response = new AjaxResponseBody();	
		response.setResult(cat);
		response.setMsg("Success");
		response.setSuccess(true);
		return new ResponseEntity<AjaxResponseBody>(response,HttpStatus.OK);
	}
	
	@GetMapping(value = "/category")
	public ResponseEntity<AjaxResponseBody> findAll() {
		List<Category> listCat= catService.findAll();
		AjaxResponseBody response = new AjaxResponseBody();	
		response.setResult(listCat);
		response.setMsg("Success");
		response.setSuccess(true);
		return new ResponseEntity<AjaxResponseBody>(response , HttpStatus.OK);
	}
	
//	@PostMapping(value = "/update")
//    public ResponseEntity<AjaxResponseBody> updateByid(@RequestBody LinkedHashMap<String,Object> dataJson){
//        Employee emp= empService.findById(dataJson.get("id").toString());
//        emp.setAddress(dataJson.get("address").toString());
//        emp.setCategory((int)dataJson.get("category"));
//        emp.setName(dataJson.get("name").toString());
//        AjaxResponseBody response = new AjaxResponseBody();	
//        response.setResult(emp);
//        response.setMsg("Success");
//        response.setSuccess(true);
//        return new ResponseEntity<AjaxResponseBody>(response,HttpStatus.OK);
//    }
}
