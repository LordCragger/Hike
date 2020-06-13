package com.cotiviti.trainee.hike.app.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cotiviti.trainee.hike.app.model.AuthenticationRequest;
import com.cotiviti.trainee.hike.app.model.AuthenticationResponse;
import com.cotiviti.trainee.hike.app.model.Message;
import com.cotiviti.trainee.hike.app.model.Roles;
import com.cotiviti.trainee.hike.app.model.Users;
import com.cotiviti.trainee.hike.app.repository.UsersRepository;
import com.cotiviti.trainee.hike.app.service.impl.MyUserDetailsService;
import com.cotiviti.trainee.hike.app.util.JwtUtil;

@CrossOrigin()
@RestController
public class LoginController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UsersRepository usersRepository;

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public ResponseEntity<Message> message() {
		Message msg = new Message();
		System.out.println("Hello in hello request-------------------------");
		msg.setMessage("Hello Message");
		return new ResponseEntity<>(msg, HttpStatus.OK);

	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect Username or Password", e);
		}

		Set<String> roles = new HashSet<>();
		Users user = usersRepository.findByUserName(authenticationRequest.getUsername());
		Set<Roles> rolesSet = new HashSet<>();
		rolesSet = user.getRoles();
		for (Roles r : rolesSet) {
			roles.add(r.getRole());
		}

		UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		String jwt = jwtUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt, roles));
	}

}
