package com.rohan.Mapper;

import java.time.LocalDate;

import com.rohan.Dto.borrowRecordDto;
import com.rohan.Entity.bookEntity;
import com.rohan.Entity.borrowRecordEntity;
import com.rohan.Entity.memberEntity;

public class borrowMapper {
	
	
	
	public static borrowRecordDto toDto(borrowRecordEntity borrowrecord)
	{
		borrowRecordDto borrowrecordDto=new borrowRecordDto();
		borrowrecordDto.setId(borrowrecord.getId());
		borrowrecordDto.setBookId(borrowrecord.getBook().getId());
		borrowrecordDto.setBookTitle(borrowrecord.getBook().getTitle());
		borrowrecordDto.setMemberId(borrowrecord.getMember().getId());
		borrowrecordDto.setMemberName(borrowrecord.getMember().getName());
		borrowrecordDto.setBorrowDate(borrowrecord.getBorrowDate());
		borrowrecordDto.setReturndate(borrowrecord.getReturnDate());
		borrowrecordDto.setStatus(borrowrecord.getStatus());

		return borrowrecordDto;
	}
	
	
	
	public static borrowRecordEntity toEntity(borrowRecordDto borrowRecordDto,bookEntity book,memberEntity member)
	{
		borrowRecordEntity record=new borrowRecordEntity();
		record.setBook(book);
		record.setMember(member);
		record.setBorrowDate(LocalDate.now());
		record.setStatus("BORROWED");

		return record;
	}
	
	

}
