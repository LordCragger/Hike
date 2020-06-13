package com.cotiviti.trainee.hike.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cotiviti.trainee.hike.app.model.Users;



public interface UsersRepository extends JpaRepository<Users, Integer>{
	
	Users findByUserName(String userName);
	
}
