package com.example.main.controllers;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.main.models.Category;
import com.example.main.models.Employee;
import com.example.main.models.pojo.AjaxResponseBody;
import com.example.main.services.CategoryService;
import com.example.main.services.EmployeeServices;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class EmployeeController {
	
	@Autowired
	private ObjectMapper mapper = new ObjectMapper();
	@Autowired
	private EmployeeServices empService = new EmployeeServices();
	@Autowired
	private CategoryService catService = new CategoryService();
	
	private AjaxResponseBody response = new AjaxResponseBody();	

	private HttpStatus status;
	
	private void setResponse(Object obj, String status , boolean success) {
		response.setResult(obj);
		response.setMsg(status);
		response.setSuccess(success);
	}
	
	@PostMapping(value = "/employee/save")
	public ResponseEntity<AjaxResponseBody> save(@RequestBody LinkedHashMap<String,Object> dataJson){
		Employee emp = mapper.convertValue(dataJson, Employee.class);
		Category checkCategory = catService.findByCategoryCode((int)dataJson.get("category"));
		if(checkCategory != null) {
			empService.saveOne(emp);
			setResponse(emp, "Success", true);
			status = HttpStatus.OK;
		}else {
			setResponse(new ArrayList<Employee>(), "Category not found", false);
            status = HttpStatus.NOT_FOUND;
		}
		
		return new ResponseEntity<AjaxResponseBody>(response,status);
	}
	
	@GetMapping(value = "/employee")
	public ResponseEntity<AjaxResponseBody> findAll() {
		List<LinkedHashMap<String, Object>> listEmp = empService.findAllWithJoin();
		setResponse(listEmp, "Success", true);
		return new ResponseEntity<AjaxResponseBody>(response , HttpStatus.OK);
	}
	
	@PostMapping(value = "/employee/update")
    public ResponseEntity<AjaxResponseBody> updateByid(@RequestBody LinkedHashMap<String,Object> dataJson){
		//cast int to bigInt 
		BigInteger id = new BigInteger(dataJson.get("id").toString());
		BigInteger salary = new BigInteger(dataJson.get("salary").toString());
        Employee emp= empService.findById(id);
        if(emp != null) {
        	 emp.setCategory((int)dataJson.get("category"));
             emp.setName(dataJson.get("name").toString());
             emp.setSalary(salary);
             empService.saveOne(emp);
       
             setResponse(emp, "Success", true);
             status = HttpStatus.OK;
        }else {
        	setResponse(new ArrayList<Employee>(), "Data not found", false);
        	status = HttpStatus.NOT_FOUND;
        }
       
        return new ResponseEntity<AjaxResponseBody>(response, status);
    }
	
	@GetMapping(value = "/employee/{id}")
	public ResponseEntity<AjaxResponseBody> findById(@PathVariable BigInteger id) {
		LinkedHashMap<String,Object> emp = empService.FindByidWithJoin(id);	
		if(emp != null) {
			setResponse(emp, "Success", true);
			status = HttpStatus.OK;
		}else {
			setResponse(new ArrayList<Employee>(), "Data not found", false);
			status = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<AjaxResponseBody>(response , status);
	}

	
	
}
