package com.cotiviti.trainee.hike.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cotiviti.trainee.hike.app.model.HikeDetails;

public interface HikeDetailsRepository extends JpaRepository<HikeDetails, Integer> {
	List<HikeDetails> findByIfDeleted(String string);
	HikeDetails findByName(String name);
}
