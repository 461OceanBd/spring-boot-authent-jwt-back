package com.rmrtechs.springbootauthentjwtback.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
	
	private Long id;
	private String email;
	private String password; // TODO : use jackson to conditionnaly serialize null properties
	private String role; 
	private LocalDateTime creationDate;
}
