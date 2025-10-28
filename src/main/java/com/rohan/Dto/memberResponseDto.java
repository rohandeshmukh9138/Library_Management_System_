package com.rohan.Dto;

import java.util.List;

import lombok.Data;

@Data
public class memberResponseDto {

	public List<memberDto> content;

	public int pageNumber;

	public long totalElements;

	public int totalPages;

	public boolean lastPage;

	
}
