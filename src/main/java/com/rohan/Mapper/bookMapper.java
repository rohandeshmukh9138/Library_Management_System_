package com.rohan.Mapper;

import com.rohan.Dto.bookDto;
import com.rohan.Entity.bookEntity;

public class bookMapper {

	public static bookDto toDto(bookEntity bookentity)
	{	
		bookDto dto= new bookDto();
		dto.setId(bookentity.getId());
		dto.setAuthor(bookentity.getAuthor());
		dto.setAvailableCopies(bookentity.getAvailableCopies());
		dto.setTitle(bookentity.getTitle());
		dto.setTotalCopies(bookentity.getTotalCopies());
		return dto;
	}
	
	public static bookEntity toEntity(bookDto bookdto)
	{
		bookEntity bookentity=new bookEntity();
		bookentity.setId(bookdto.getId());
		bookentity.setAuthor(bookdto.getAuthor());
		bookentity.setAvailableCopies(bookdto.getAvailableCopies());
		bookentity.setTitle(bookdto.getTitle());
		bookentity.setTotalCopies(bookdto.getTotalCopies());
		
		
		
		return bookentity;
	}
	
}
