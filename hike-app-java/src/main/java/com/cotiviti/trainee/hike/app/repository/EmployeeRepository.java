package com.cotiviti.trainee.hike.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cotiviti.trainee.hike.app.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	List<Employee> findByIfDeleted(String deleteStatus);

	Employee findByInoAndIfDeleted(String ino, String deleteStatus);
	
	@Query(value="SELECT * FROM EMPLOYEE ORDER BY RAND() LIMIT 10",nativeQuery=true)
    List<Employee> customQuery();

}
