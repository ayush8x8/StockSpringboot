package com.phase3ayush.ayush.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
//import com.stockexchange.phase3.JwtUserDetailsService;
//
//import com.stockexchange.phase3.*;
//import com.stockexchange.phase3.JwtTokenUtil;
//import com.stockexchange.phase3.JwtRequest;
//import com.stockexchange.phase3.JwtResponse;

import com.phase3ayush.ayush.dao.User1Repository;
import com.phase3ayush.ayush.entities.User1;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	@Autowired
	User1Repository userRepo;
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	// without dto below public ResponseEntity<?> saveUser(@RequestBody UserDto user) throws Exception {
	public ResponseEntity<?> saveUser(@RequestBody User1 user) throws Exception {
//USer1 is your user pojo entity 
		return ResponseEntity.ok(userDetailsService.save(user));
	}
//if not using dto ,then for register name and not user name
	//user username for authenticate but
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST,headers = "Accept=application/json")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());
		
		final User1 user = userRepo.findByName(authenticationRequest.getUsername());
		
		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token, user));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
