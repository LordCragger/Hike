package com.cotiviti.trainee.hike.app.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cotiviti.trainee.hike.app.model.Employee;
import com.cotiviti.trainee.hike.app.model.HikeDetails;
import com.cotiviti.trainee.hike.app.model.HikeRequest;
import com.cotiviti.trainee.hike.app.repository.EmployeeRepository;
import com.cotiviti.trainee.hike.app.repository.HikeDetailsRepository;
import com.cotiviti.trainee.hike.app.repository.HikeRequestRepository;
import com.cotiviti.trainee.hike.app.service.HikeRequestService;

@Service
public class HikeRequestImpl implements HikeRequestService {

	@Autowired
	private HikeRequestRepository hikeRequestRepo;

	@Autowired
	private HikeDetailsRepository hikeDetailsRepo;

	@Autowired
	private EmployeeRepository empRepo;

	@Override
	public List<HikeRequest> findByIfDeleted(String string) {
		List<HikeRequest> hikeRequests = hikeRequestRepo.findByIfDeleted("No");
//		System.out.println("Hike Requests: "+hikeRequests.);
		for(HikeRequest hr: hikeRequests) {
			hr.setHikeName(hr.getHikeDetails().getName());
			hr.setEmployeeIno(hr.getEmployee().getIno());
		}
		return hikeRequests;
	}

	@Override
	public HikeRequest save(HikeRequest hikeRequest) {
		Employee emp = empRepo.findByInoAndIfDeleted(hikeRequest.getEmployeeIno(), "No");
		Optional<HikeDetails> hikeDetails = hikeDetailsRepo.findById(Integer.parseInt(hikeRequest.getHikeName()));
		hikeRequest.setEmployee(emp);
		hikeRequest.setHikeDetails(hikeDetails.get());
		hikeRequest.setHikeRequestDate(LocalDate.now());
		return hikeRequestRepo.save(hikeRequest);
	}

	@Override
	public void deleteHikeRequesterById(Integer id) {
		Optional<HikeRequest> hikeRequest = hikeRequestRepo.findById(id);
		hikeRequest.get().setIfDeleted("Yes");
		hikeRequestRepo.save(hikeRequest.get());
	}

	@Override
	public List<HikeRequest> randomHikeSelector() {
		// TODO Auto-generated method stub
		List<HikeRequest> randomHikes = hikeRequestRepo.randomHikeSelector();
		for(HikeRequest hr:randomHikes) {
			hr.setEmployeeIno(hr.getEmployee().getName());
			hr.setHikeName(hr.getHikeDetails().getName());
		}
		return randomHikes;
	}
}
