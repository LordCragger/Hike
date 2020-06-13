package com.cotiviti.trainee.hike.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cotiviti.trainee.hike.app.model.Employee;
import com.cotiviti.trainee.hike.app.repository.EmployeeRepository;
import com.cotiviti.trainee.hike.app.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	@Override
	public List<Employee> findByIfDeleted(String name) {
		return employeeRepository.findByIfDeleted("No");
	}

	@Override
	public Optional<Employee> findById(Integer id) {
		return employeeRepository.findById(id);
	}

	@Override
	public Employee save(Employee employee) {
		employee.setIfDeleted("No");
//		check if ino already exists------
		return employeeRepository.save(employee);
	}

	@Override
	public void delete(Integer id) {
		Optional<Employee> emp = employeeRepository.findById(id);
		emp.get().setIfDeleted("Yes");
		employeeRepository.save(emp.get());
	}

	@Override
	public Employee findByInoAndIfDeleted(String ino, String deleteStatus) {
		return employeeRepository.findByInoAndIfDeleted(ino,deleteStatus);
	}
	
	 @Override
	    public List<Employee> customQuery(){
	        return employeeRepository.customQuery();
	    }

	@Override
	public Boolean ifInumberPreExist() {
		// TODO Auto-generated method stub
		return null;
	}
}
