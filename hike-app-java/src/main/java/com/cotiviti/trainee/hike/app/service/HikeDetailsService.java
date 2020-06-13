package com.cotiviti.trainee.hike.app.service;

import java.util.List;
import java.util.Optional;

import com.cotiviti.trainee.hike.app.model.HikeDetails;

public interface HikeDetailsService {

	List<HikeDetails> findByIfDeleted(String string);

	HikeDetails save(HikeDetails hikeDetails);

	Optional<HikeDetails> deleteById(Integer id);
}
