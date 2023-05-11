package com.example.Service.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.Service.entites.Hotel;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {

	@GetMapping("/hotel/{hotelId}")
	Hotel getHotel(@PathVariable("hotelId") String hotelId);
	
	
}
