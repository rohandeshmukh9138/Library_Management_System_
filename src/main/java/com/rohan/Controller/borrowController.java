package com.rohan.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rohan.Service.borrowService;

@RestController
public class borrowController {

	@Autowired
	borrowService borrowservice;
	
	//to save borrow book
	@PostMapping("/borrowbook")
	public ResponseEntity<String> borrowBook(@RequestParam Long bookId,@RequestParam Long memberId)
	{
		String borrowBook = borrowservice.borrowBook(memberId, bookId);
		
		return ResponseEntity.ok(borrowBook);
	}
	
	
	//to save return book
	@PatchMapping("/returnBook/{recordId}")
	public ResponseEntity<String> returnBook(@PathVariable Long recordId)
	{
		String returnBook = borrowservice.returnBook(recordId);
		return ResponseEntity.ok(returnBook);
	}
	
	//to get all borrowed books by members
	@GetMapping("/getallborrowbooks/{memberId}")
	public ResponseEntity<Map<String, Object>> getAllBooksBorrowedByMembers(@PathVariable Long memberId)
	{
		List<String> list= borrowservice.getAllBooksBorrowedByMembers(memberId);
		
		Map<String, Object> map=new HashMap<>();
		map.put("Book Details are:",list);
		
		return ResponseEntity.ok(map);
	}
}
