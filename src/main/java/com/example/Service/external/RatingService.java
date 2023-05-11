package com.example.Service.external;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.Service.entites.Rating;

@Service
@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

//	@GetMapping("/rating/{ratingId}")
//	Rating getRating(@PathVariable("ratingId") String ratingId);
	
	@PostMapping("/rating")
	ResponseEntity<Rating> createrating(Rating values);
	
	@PutMapping("/rating/{ratingId}")
	ResponseEntity<Rating> updateRating(@PathVariable String ratingId,Rating rating);
	
	@DeleteMapping("/rating/{ratingId}")
	public  Void deleteRating(@PathVariable String ratingId);
	
}
