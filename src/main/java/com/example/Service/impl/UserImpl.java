package com.example.Service.impl;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.Service.entites.Hotel;
import com.example.Service.entites.Rating;
import com.example.Service.entites.User;
import com.example.Service.exceptions.ResourceNotFoundException;
import com.example.Service.external.HotelService;
import com.example.Service.external.RatingService;
import com.example.Service.repositories.UserRepositories;
import com.example.Service.service.UserServices;


@Service
public class UserImpl implements UserServices {

	@Autowired
	private UserRepositories repositories;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelService;

	@Autowired
	private RatingService ratingService;
	
	private Logger logger=LoggerFactory.getLogger(UserImpl.class);
	
	@Override
	public User AddUser(User user) {
		String randomeUserId = UUID.randomUUID().toString();
		user.setUserId(randomeUserId);
		return repositories.save(user);
	}

	@Override
	public User GetUserById(String userId) {

		User user = repositories.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User Id Is Not Found On Server !!" + userId));

		Rating [] ratingOfUser = restTemplate
				.getForObject("http://RATING-SERVICE/rating/users/" + user.getUserId(), Rating [].class);

		logger.info("{}", ratingOfUser);	
		List<Rating> ratings = Arrays.stream(ratingOfUser).toList();

		List<Rating> ratingList = ratings.stream().map(rating -> {

			//ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/getSingleHotel/" + rating.getHotelId(), Hotel.class);
			Hotel hotel = hotelService.getHotel(rating.getHotelId());
			
			rating.setHotel(hotel);
           return rating;
		}).collect(Collectors.toList());
		user.setRatings(ratingList);
		return user;
	}

	
	
	@Override
	public List<User> getAllUser() {
		return repositories.findAll();
	}

	@Override
	public User UpdateUser(User user) {
		return repositories.save(user);
	}

	@Override
	public void deleteStudent(String userId) {
	this.repositories.deleteById(userId);
	}

}
