package com.phase3ayush.ayush.controller;

import java.util.ArrayList;
//add jwt.secret=abcd to application properties
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.phase3ayush.ayush.dao.User1Repository;
import com.phase3ayush.ayush.entities.User1;
//import com.stockexchange.phase3.*;
@Service
public class JwtUserDetailsService implements UserDetailsService {
	@Autowired
	User1Repository userrepo;

	@Autowired
	User1Repository userrepo2;	
	@Autowired
	
	private PasswordEncoder bcryptEncoder;
	
	public Collection<? extends GrantedAuthority> getAuthorities() {
	       
     User1 user = new User1() ;
     
     List<SimpleGrantedAuthority> authorities = new ArrayList<>();
      
     
         authorities.add(new SimpleGrantedAuthority(user.getRole()));
    
      
     return authorities;
 }
	@Override
	public Userdetails1 loadUserByUsername(String username) throws UsernameNotFoundException {
		User1 user = userrepo2.findByName(username);
		
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	//non dto code below	//return new org.springframework.security.core.userdetails.User(user.getname(), user.getpassword(),
			//	new ArrayList<>());
		return new Userdetails1(user);//you have to implement userdetails if you dont want to use dto
	}

//implement without dto	public com.stockexchange.phase3.User1 save(UserDto user) {
	public User1 save(User1 user) {
		User1 newUser = new User1();
		//newUser.setname(user.getUsername());
		//newUser.setpassword(bcryptEncoder.encode(user.getPassword()));
	    newUser.setName(user.getName());
	    newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
	    newUser.setEmail(user.getEmail());
		newUser.setRole(user.getRole());
		newUser.setMobilenumber(user.getMobilenumber());
		newUser.setConfirmed(user.getConfirmed());
		newUser.setAdmin(user.getAdmin());
		return userrepo.save(newUser);
	}





	}
