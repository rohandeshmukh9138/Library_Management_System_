package com.rohan.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rohan.Entity.bookEntity;
import com.rohan.Entity.borrowRecordEntity;
import com.rohan.Entity.memberEntity;
import com.rohan.Repo.bookRepo;
import com.rohan.Repo.borrowRecordRepo;
import com.rohan.Repo.memberRepo;

@Service
public class borrowService {

	
	@Autowired
	bookRepo bookrepo;
	
	@Autowired
	memberRepo memberrepo;
	
	@Autowired
	borrowRecordRepo borrowrecordrepo;
	
	//to Borrow a Book
	public String borrowBook(Long memberId,Long bookID)
	{
	 bookEntity bookentity=bookrepo.findById(bookID).orElseThrow(()-> new RuntimeException("Book not Found"));
	 
	 memberEntity memberentity = memberrepo.findById(memberId).orElseThrow(()-> new RuntimeException("Member not found"));
	 
	 borrowRecordEntity borrowrecord=new borrowRecordEntity();
	 borrowrecord.setBook(bookentity);
	 borrowrecord.setMember(memberentity);
	 borrowrecord.setBorrowDate(LocalDate.now());
	 borrowrecord.setStatus("Borrowed");
	 
	 borrowrecordrepo.save(borrowrecord);
	 
	 return "Book Borrowed Successfully..!!";
	 
	}
	
	
	//to return a book
	public String returnBook(Long recordId)
	{
		borrowRecordEntity record = borrowrecordrepo.findById(recordId).orElseThrow(()-> new RuntimeException("Borrow record not Found"));
		record.setReturnDate(LocalDate.now());
		record.setStatus("Returned");
		borrowrecordrepo.save(record);
		
		return "Booked return Successfully..!!";
	}
	
	
	//to get all borrowed books by members
	
	public List<String> getAllBooksBorrowedByMembers(Long memberId)
	{
		List<borrowRecordEntity> borrowrecord = borrowrecordrepo.findByMemberId(memberId);
		
		return borrowrecord.stream().filter(r-> "Borrowed".equalsIgnoreCase(r.getStatus())).map(r-> r.getBook().getTitle()).collect(Collectors.toList());
	}
	
	
}
