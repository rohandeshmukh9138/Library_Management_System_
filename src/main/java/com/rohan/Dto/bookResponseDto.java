package com.rohan.Dto;

import java.util.List;

import lombok.Data;

@Data
public class bookResponseDto {

public List<bookDto> content;

public int pageNumber;

public long totalElements;

public int totalPages;

public boolean lastPage;
	
}
