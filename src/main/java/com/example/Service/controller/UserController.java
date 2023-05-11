package com.example.Service.controller;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Service.entites.User;
import com.example.Service.impl.UserImpl;
import com.example.Service.service.UserServices;

//import ch.qos.logback.classic.Logger;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/user")
public class UserController {

	private Logger logger=LoggerFactory.getLogger(UserImpl.class);
	
	@Autowired
	public UserServices services;
	
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user){
		User addUser = services.AddUser(user);
	    return ResponseEntity.status(HttpStatus.CREATED).body(addUser);
	}
	
	
	int retrycount=1;
	@GetMapping("/{userId}")
//	@CircuitBreaker(name = "ratingHotelBreaker",fallbackMethod = "ratingHotelfallback")
//	@Retry(name = "ratingHotelService",fallbackMethod = "ratingHotelfallback")
	@RateLimiter(name = "userRateLimiter",fallbackMethod =  "ratingHotelfallback")
	public ResponseEntity<User> getSingleUserById(@PathVariable String userId){
		logger.info("Get Single user handler: UserController");
		logger.info("Retry Count: {}",retrycount);
		retrycount++;
		User user = services.GetUserById(userId);
	    return ResponseEntity.ok(user);
	}
	

	//creating fall back method for circuitbreaker
	public ResponseEntity<User> ratingHotelfallback(String userId,Exception exception){
		//logger.info("Fallback is executed beacuse service is down:",exception.getMessage());
		User user=User.builder().email("fake@gmail.com").name("fake").userId("20152").about("This is a fake beacuse service is down ").build();
		return new  ResponseEntity<>(user,HttpStatus.OK);
	}
	
	
	@GetMapping
	public ResponseEntity<List<User>> getAllUser(){
		List<User> user = services.getAllUser();
	    return ResponseEntity.ok(user);
	}
	
	@PutMapping("/Update")
	public ResponseEntity<User> updateUser(@RequestBody User user){
		User addUser = services.AddUser(user);
	    return ResponseEntity.status(HttpStatus.CREATED).body(addUser);
	}
	
	/*
	 * @DeleteMapping("/{userId}") public void deleteUser(@PathVariable("userId")
	 * String userId) { this.services.deleteStudent(userId); }
	 */
	
}
