package com.rohan.Mapper;

import java.time.LocalDate;

import com.rohan.Dto.borrowRecordDto;
import com.rohan.Entity.bookEntity;
import com.rohan.Entity.borrowRecord;
import com.rohan.Entity.memberEntity;

public class borrowMapper {
	
	
	
	public static borrowRecordDto toDto(borrowRecord borrowrecord)
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
	
	
	
	public static borrowRecord toEntity(borrowRecordDto borrowRecordDto,bookEntity book,memberEntity member)
	{
		borrowRecord record=new borrowRecord();
		record.setBook(book);
		record.setMember(member);
		record.setBorrowDate(LocalDate.now());
		record.setStatus("BORROWED");

		return record;
	}
	
	

}
