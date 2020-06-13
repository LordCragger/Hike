package com.cotiviti.trainee.hike.app.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cotiviti.trainee.hike.app.model.Roles;
import com.cotiviti.trainee.hike.app.model.Users;
import com.cotiviti.trainee.hike.app.repository.UsersRepository;



@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UsersRepository usersRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
		
		Users user = usersRepository.findByUserName(userName);
		Set<Roles> roles = new HashSet<>();
		roles = user.getRoles();
		for(Roles r:roles) {
			System.out.println("********************Roles**************************");
			System.out.println("Role:-"+r.getRole());
		}
		
		return new User(user.getUserName(), user.getPassword(), new ArrayList<>());
	}

}
