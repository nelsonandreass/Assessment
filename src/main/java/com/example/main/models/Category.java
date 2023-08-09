package com.example.main.models;


import java.util.Date;
import java.util.List;
import java.util.UUID;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Category {
	
	@Id
	private String id = UUID.randomUUID().toString();
	//@OneToMany(mappedBy = "cateogry")
	//private List<Employee> employees;
	private int categoryCode;
	private String categoryDescription;
	private float bonus;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(int categoryCode) {
		this.categoryCode = categoryCode;
	}
//	
//	public List<Employee> getEmployees() {
//		return employees;
//	}
//	public void setEmployees(List<Employee> employees) {
//		this.employees = employees;
//	}
	public String getCategoryDescription() {
		return categoryDescription;
	}
	
	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}
	
	public float getBonus() {
		return bonus;
	}
	public void setBonus(float bonus) {
		this.bonus = bonus;
	}

	
	
}
