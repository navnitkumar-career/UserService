package com.example.Service.entites;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rating {

	private String ratingId;
	private String userId;
	private String hotelId;
	private int rating;
	private String feedback;
	
	private Hotel hotel;

	@Override
	public String toString() {
		return "Rating [ratingId=" + ratingId + ", userId=" + userId + ", hotelId=" + hotelId + ", rating=" + rating
				+ ", feedback=" + feedback + ", hotel=" + hotel + "]";
	}
   	
	
}
