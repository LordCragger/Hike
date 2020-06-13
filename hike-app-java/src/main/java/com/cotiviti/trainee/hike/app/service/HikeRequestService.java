package com.cotiviti.trainee.hike.app.service;

import java.util.List;

import com.cotiviti.trainee.hike.app.model.HikeRequest;

public interface HikeRequestService {

	List<HikeRequest> findByIfDeleted(String name);

	HikeRequest save(HikeRequest hikeRequest);
	
	void deleteHikeRequesterById(Integer id);
	
	List<HikeRequest> randomHikeSelector(); 
	
}
