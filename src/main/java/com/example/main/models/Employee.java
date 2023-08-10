package com.example.main.models;


import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;



import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;

@Entity
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigInteger id;
	private String name;
	private int category;
	private BigInteger salary;

	public Employee() {}
	public Employee(String string, int i, BigInteger j) {
		// TODO Auto-generated constructor stub
		Random random = new Random();
		this.id = new BigInteger(String.valueOf(random.nextInt())) ; 
		this.name = string;
		this.category = i;
		this.salary = j;
	}
	public BigInteger getSalary() {
		return salary;
	}
	public void setSalary(BigInteger salary) {
		this.salary = salary;
	}
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	
	
	
}
