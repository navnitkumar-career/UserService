package com.example.Service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.example.Service.entites.Rating;
import com.example.Service.external.RatingService;

@SpringBootTest
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	private RatingService ratingService;

	@Test
	void creatRating() {

		Rating rating=Rating.builder().ratingId("653").rating(9).userId("").hotelId("").feedback("the testing").build();
	     ResponseEntity<Rating> responseEntity = ratingService.createrating(rating);
	    System.out.println( responseEntity.getStatusCode());
		System.out.println("new rating created");
		
	}
 
}
