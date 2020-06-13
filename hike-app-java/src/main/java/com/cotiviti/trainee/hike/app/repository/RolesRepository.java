package com.cotiviti.trainee.hike.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cotiviti.trainee.hike.app.model.Roles;



public interface RolesRepository extends JpaRepository<Roles, Integer> {
	
	Optional<Roles> findById(Integer id);

}
