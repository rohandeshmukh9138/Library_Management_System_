package com.rohan.Dto;

import lombok.Data;

@Data
public class bookDto {
	
	private Long id;
	
	private String title;
	
	private String author;
	
	private int totalCopies;
	
	private int availableCopies;

}
