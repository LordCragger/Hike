package com.cotiviti.trainee.hike.app.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cotiviti.trainee.hike.app.model.Employee;
import com.cotiviti.trainee.hike.app.model.HikeDetails;
import com.cotiviti.trainee.hike.app.repository.EmployeeRepository;
import com.cotiviti.trainee.hike.app.repository.HikeDetailsRepository;
import com.cotiviti.trainee.hike.app.service.HikeDetailsService;



@Service
public class HikeDetailsServiceImpl implements HikeDetailsService {

	@Autowired
	private HikeDetailsRepository hikeDetailsRepo;

	@Autowired
	private EmployeeRepository empRepo;

	@Override
	public List<HikeDetails> findByIfDeleted(String name) {
		List<HikeDetails> hikeDetails = hikeDetailsRepo.findByIfDeleted("No");
		for(HikeDetails hd:hikeDetails) {
			hd.setHikeCoordinator(hd.getEmployee().getIno());
		}
		return hikeDetails;
	}

	@Override
	public HikeDetails save(HikeDetails hikeDetails) {
		
		Employee employee = empRepo.findByInoAndIfDeleted(hikeDetails.getHikeCoordinator(), "No");
		System.out.println("EMployee ino:"+hikeDetails.getHikeCoordinator()+" ---- "+employee.getName());
		hikeDetails.setEmployee(employee);
		hikeDetails.setIfDeleted("No");
		return hikeDetailsRepo.save(hikeDetails);
	}

	@Override
	public Optional<HikeDetails> deleteById(Integer id) {
		Optional<HikeDetails> hd = hikeDetailsRepo.findById(id);
		hd.get().setIfDeleted("Yes");
		hikeDetailsRepo.save(hd.get());
		return hd;
	}
}
