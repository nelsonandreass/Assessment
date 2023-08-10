package com.example.main.services;

import java.math.BigInteger;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.main.models.Employee;
import com.example.main.repositories.EmployeeRepository;

@Service
@Transactional
public class EmployeeServices {
	
	@Autowired
	private EmployeeRepository employeeRepo;
	
	
	public void saveOne(Employee employee) {
		Employee emp = employeeRepo.save(employee);
	}
	
	public List<Employee> findAll(){
        List<Employee> listUsers = employeeRepo.findAll();
        return listUsers;
    }
	
	public Employee findById(BigInteger id){
        Employee emp = employeeRepo.findByid(id);
        return emp;
    }
	
	public LinkedHashMap<String,Object> FindByidWithJoin(BigInteger id){
		LinkedHashMap<String,Object> emp = employeeRepo.FindByidWithJoin(id);
		String formattedValue = String.format("%.0f", emp.get("bonus"));
		BigInteger integerValue = new BigInteger(formattedValue);
		emp.put("bonus", integerValue);
        return emp;
    }
	
	public List<LinkedHashMap<String,Object>> findAllWithJoin(){
        List<LinkedHashMap<String,Object>> emp = employeeRepo.FindAllWithJoin();
        for(LinkedHashMap<String,Object> row :emp) {
        	for(Map.Entry<String, Object> entry : row.entrySet() ) {
        		if( entry.getKey().toString().equalsIgnoreCase("bonus")) {
        			String formattedValue = String.format("%.0f", entry.getValue());
        			BigInteger integerValue = new BigInteger(formattedValue);
        			entry.setValue(integerValue);
        		}
        	}
        }
        return emp;
   }

	
}
