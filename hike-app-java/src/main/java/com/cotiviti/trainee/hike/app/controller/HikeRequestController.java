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

import com.cotiviti.trainee.hike.app.model.HikeRequest;
import com.cotiviti.trainee.hike.app.service.HikeRequestService;

@CrossOrigin()
@RestController
public class HikeRequestController {

	@Autowired
	private HikeRequestService hikeRequestService;

	@GetMapping("/hikeRequest")
	public ResponseEntity<List<HikeRequest>> findAll() {
		List<HikeRequest> hikeRequests = hikeRequestService.findByIfDeleted("No");
		return new ResponseEntity<List<HikeRequest>>(hikeRequests, HttpStatus.OK);

	}
	
	@GetMapping("/randomHikeSelector")
	public ResponseEntity<List<HikeRequest>> randomHikeSelector(){
		List<HikeRequest> randomHikeRequests = hikeRequestService.randomHikeSelector();
		return new ResponseEntity<List<HikeRequest>>(randomHikeRequests, HttpStatus.OK);
	}

	@RequestMapping(path = "/hikeRequest", method = { RequestMethod.POST, RequestMethod.PUT })
	public ResponseEntity<HikeRequest> save(@RequestBody HikeRequest hikeRequest) {
		System.out.println("Request Inc");
		hikeRequest.setIfDeleted("No");
		HikeRequest temphikeRequest = hikeRequestService.save(hikeRequest);
		return new ResponseEntity<HikeRequest>(temphikeRequest, HttpStatus.OK);

	}

	@DeleteMapping("/hikeRequest/{id}")
	public void delete(@PathVariable Integer id) {
		System.out.println("Delete Request "+id);
		hikeRequestService.deleteHikeRequesterById(id);
//		System.out.println("ansssss "+optionalHikeRequest.get().getHikeName());
//		if (!optionalHikeRequest.isPresent()) {
//			return new ResponseEntity<HikeRequest>(HttpStatus.NOT_FOUND);
//		} else {
//			optionalHikeRequest.get().setIfDeleted("Yes");
//			hikeRequestService.save(optionalHikeRequest.get());
//			return new ResponseEntity<HikeRequest>(optionalHikeRequest.get(), HttpStatus.OK);

		}
}
