package com.example.Service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Service.entites.User;


public interface UserServices {

	User AddUser(User user);
	
	User GetUserById(String  userId);
	
	List<User> getAllUser();
	
	User UpdateUser(User user);
	
	public void deleteStudent(String  userId);
 	
	
}
