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
	 
	@Query("SELECT NEW MAP(e.id as id, e.name as name, e.salary as salary , CONCAT(c.categoryCode,':',c.categoryDescription) as gradeCode, (e.salary * c.bonus) + e.salary as bonus)"
			+ "FROM Employee e "
			+ "INNER JOIN Category c on e.category= c.categoryCode where e.id=:param")
	public LinkedHashMap<String, Object> FindByidWithJoin(  @Param("param") BigInteger id);
	 
	
	@Query("SELECT NEW MAP(e.id as id, e.name as name, e.salary as salary , CONCAT(c.categoryCode,':',c.categoryDescription) as gradeCode, (e.salary * c.bonus) + e.salary as bonus)"
			+ "FROM Employee e "
			+ "INNER JOIN Category c on e.category= c.categoryCode")
	public List<LinkedHashMap<String, Object>> FindAllWithJoin();
}
