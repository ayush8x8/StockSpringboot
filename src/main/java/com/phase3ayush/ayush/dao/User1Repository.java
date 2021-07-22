package com.phase3ayush.ayush.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.phase3ayush.ayush.entities.Company;
import com.phase3ayush.ayush.entities.User1;

public interface User1Repository extends JpaRepository<User1, Long> {
	public User1 findByName(String userName);
	
	public User1 findByNameAndPassword(String name, String password);
}
