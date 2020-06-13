package com.cotiviti.trainee.hike.app.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.cotiviti.trainee.hike.app.model.HikeRequest;

public interface HikeRequestRepository extends JpaRepository<HikeRequest, Integer> {
	List<HikeRequest> findByIfDeleted(String name);
	
	@Query(value="SELECT * FROM HIKE_REQUEST ORDER BY RAND() LIMIT 3",nativeQuery=true)
    List<HikeRequest> randomHikeSelector();
}
