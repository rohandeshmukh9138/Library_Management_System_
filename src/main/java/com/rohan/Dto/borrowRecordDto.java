package com.rohan.Dto;

import java.time.LocalDate;

import lombok.Data;
@Data
public class borrowRecordDto {

	
	private Long id;
	
	private Long bookId;
	
	private Long memberId;
	
	private String bookTitle;
	
	private String memberName;
	
	private LocalDate borrowDate;
	
	private LocalDate returndate;
	
	private String status;
	
}
