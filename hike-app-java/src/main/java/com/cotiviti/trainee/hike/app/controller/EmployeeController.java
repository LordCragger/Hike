package com.cotiviti.trainee.hike.app.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.cotiviti.trainee.hike.app.model.Employee;
import com.cotiviti.trainee.hike.app.service.EmployeeService;

@CrossOrigin()
@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/selectedEmployee")
	 public ResponseEntity<List<Employee>> findTenRandom() {
        List<Employee> employees = employeeService.customQuery();
        System.out.println("Size of employee: "+employees.size());
        return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
    }

	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> findAll() {
		List<Employee> employees = employeeService.findByIfDeleted("No");
		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}

	@RequestMapping(path = "/employees", method = { RequestMethod.POST, RequestMethod.PUT })
	public ResponseEntity<Employee> save(@RequestBody Employee employee) {
		Employee tempEmployee = employeeService.save(employee);
//		boolean flag = employeeService.ifInumberPreExist();
		return new ResponseEntity<Employee>(tempEmployee, HttpStatus.OK);
	}

	@DeleteMapping("/employees/{id}")
	public void delete(@PathVariable Integer id) {
		employeeService.delete(id);
	}
}
