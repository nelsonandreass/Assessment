package com.example.main.repositories;

import java.math.BigInteger;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.main.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String>{
	
	 public Employee findByid(BigInteger id);
	 
	@Query("SELECT NEW MAP(e.name as name , c.categoryDescription as Grade) FROM Employee e "
			+ "INNER JOIN Category c on e.category= c.categoryCode where e.id=:param")
	public List<LinkedHashMap<String, Object>> FindByidJoin(  @Param("param") String id);
	 
	
	@Query("SELECT NEW MAP(e.id as ID, e.name as name, e.salary as Salary , CONCAT(c.categoryCode,':',c.categoryDescription) as GradeCode, (e.salary * c.bonus) + e.salary as Bonus)"
			+ "FROM Employee e "
			+ "INNER JOIN Category c on e.category= c.categoryCode")
	public List<LinkedHashMap<String, Object>> FindAllWithJoin();
}
