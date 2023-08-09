package com.example.main.controllers;

import java.math.BigInteger;
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

import com.example.main.models.Employee;
//import com.sgo.hsbcproject.models.Employee;
//import com.sgo.hsbcproject.models.Users;
//import com.sgo.hsbcproject.models.pojo.AjaxResponseBody;
//import com.sgo.hsbcproject.services.EmployeeServices;
import com.example.main.models.pojo.AjaxResponseBody;
import com.example.main.services.EmployeeServices;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class EmployeeController {
	
	@Autowired
	private ObjectMapper mapper = new ObjectMapper();
	@Autowired
	private EmployeeServices empService = new EmployeeServices();
	
	@RequestMapping(value="/" , method = RequestMethod.GET)
	public ResponseEntity<String> test(){
		System.out.println("test");
		return new ResponseEntity<String>("Test",HttpStatus.OK);
	}

	@PostMapping(value = "/save")
	public ResponseEntity<AjaxResponseBody> save(@RequestBody LinkedHashMap<String,Object> dataJson){
		Employee emp = mapper.convertValue(dataJson, Employee.class);
		empService.saveOne(emp);
		AjaxResponseBody response = new AjaxResponseBody();	
		response.setResult(emp);
		response.setMsg("Success");
		response.setSuccess(true);
		return new ResponseEntity<AjaxResponseBody>(response,HttpStatus.OK);
	}
	
	@GetMapping(value = "/employee")
	public ResponseEntity<AjaxResponseBody> findAll() {
		List<LinkedHashMap<String, Object>> listEmp = empService.findAllWithJoin();
		AjaxResponseBody response = new AjaxResponseBody();	
		response.setResult(listEmp);
		response.setMsg("Success");
		response.setSuccess(true);
		return new ResponseEntity<AjaxResponseBody>(response , HttpStatus.OK);
	}
	
	@PostMapping(value = "/update")
    public ResponseEntity<AjaxResponseBody> updateByid(@RequestBody LinkedHashMap<String,Object> dataJson){
		BigInteger id = new BigInteger(dataJson.get("id").toString());
		BigInteger salary = new BigInteger(dataJson.get("salary").toString());
        Employee emp= empService.findById(id);
        emp.setCategory((int)dataJson.get("category"));
        emp.setName(dataJson.get("name").toString());
        emp.setSalary(salary);
        empService.saveOne(emp);
        AjaxResponseBody response = new AjaxResponseBody();	
        response.setResult(emp);
        response.setMsg("Success");
        response.setSuccess(true);
        return new ResponseEntity<AjaxResponseBody>(response,HttpStatus.OK);
    }

	
	
}
