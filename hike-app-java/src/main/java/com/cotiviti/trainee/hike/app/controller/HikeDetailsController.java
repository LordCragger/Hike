package com.cotiviti.trainee.hike.app.controller;

import java.util.List;
import java.util.Optional;

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

import com.cotiviti.trainee.hike.app.model.HikeDetails;
import com.cotiviti.trainee.hike.app.service.HikeDetailsService;

@CrossOrigin()
@RestController
public class HikeDetailsController {

	@Autowired
	private HikeDetailsService hikeDetailsService;

	@GetMapping("/hikeDetails")
	public ResponseEntity <List<HikeDetails>> findAll() {
		List<HikeDetails> hikeDetails = hikeDetailsService.findByIfDeleted("No");
		return new ResponseEntity <List<HikeDetails>>(hikeDetails, HttpStatus.OK);
	}
 
	@RequestMapping(path = "/hikeDetails", method = { RequestMethod.POST, RequestMethod.PUT })
	public ResponseEntity<HikeDetails> save(@RequestBody HikeDetails hikeDetails) {
		HikeDetails tempHikeDetails = hikeDetailsService.save(hikeDetails);
		return new ResponseEntity<HikeDetails>(tempHikeDetails, HttpStatus.OK);
	}

	@DeleteMapping("/hikeDetails/{id}")
	public ResponseEntity<Optional<HikeDetails>> delete(@PathVariable Integer id) {
		Optional<HikeDetails> hikeDetail = hikeDetailsService.deleteById(id);
		return new ResponseEntity <>(hikeDetail, HttpStatus.OK);
	}

}
