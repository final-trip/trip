package com.ssafy.mountain.model;

import java.sql.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class MountainRequestDto {
	private String userId;
	private String mntiname;
	private int sido_code;
	private int gugun_code;

	// Getters and Setters
}
