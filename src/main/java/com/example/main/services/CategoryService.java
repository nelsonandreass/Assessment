package com.example.main.services;

import java.util.List;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.main.models.Category;
import com.example.main.repositories.CategoryRepository;

@Service
@Transactional
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	
	public void saveOne(Category cat) {
		Category category= categoryRepo.save(cat);
	}
	
	public List<Category> findAll(){
        List<Category> listCategory= categoryRepo.findAll();
        return listCategory;
    }
//	
//	public Employee findById(String id){
//        Employee emp = employeeRepo.findByid(id);
//        return emp;
//    }
	
	//public boolean save(Employee employee) {
//		Employee emp = employeeRepo.findByEmpCode(employee.getEmpCode());
//		if(emp == null) {
//			employeeRepo.save(employee);
//			for(EmployeePosition empPos : employee.getEmployeePosition()) {
//				empPosService.save(empPos);
//			}
//			for(EmployeeOrganization empOrg : employee.getEmployeeOrganization()) {
//				empOrgService.save(empOrg);
//			}
//			//for(EmployeePosition empPos : employee.getEmployeePosition()) {
//			//	empPosService.save(employee.getEmpPos());
//				empOrgService.save(employee.getEmpOrganization());
//			//}
//			fileStorageService.storeFile(employee.getFilePhoto());
//			return true;
//		}
//		else {
//			return false;
//		}
		
	//}
	
}
