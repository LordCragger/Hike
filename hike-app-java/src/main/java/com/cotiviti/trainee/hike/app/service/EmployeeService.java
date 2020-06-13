package com.cotiviti.trainee.hike.app.service;

import java.util.List;
import java.util.Optional;

import com.cotiviti.trainee.hike.app.model.Employee;

public interface EmployeeService {
	
	List<Employee> customQuery();

	List<Employee> findAll();

	Optional<Employee> findById(Integer id);

	Employee save(Employee employee);

	void delete(Integer id);

	List<Employee> findByIfDeleted(String string);

	Employee findByInoAndIfDeleted(String ino,String deleteStatus);
	
	Boolean ifInumberPreExist();

}
