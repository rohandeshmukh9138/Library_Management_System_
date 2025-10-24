package com.rohan.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rohan.Dto.bookDto;
import com.rohan.Service.bookService;


@RestController
public class bookController {

	
	@Autowired
	bookService bookservice;
	
	//to add the book
	@PostMapping("/addBook")
	public ResponseEntity<bookDto> addBook(@RequestBody bookDto bookdto)
	{
		return ResponseEntity.ok(bookservice.addBook(bookdto));	
	}
	
	//get the book by its ID
	@GetMapping("/getBook/{id}")
	public ResponseEntity<bookDto> getBook(@PathVariable Long id)
	{
		return ResponseEntity.ok(bookservice.getBook(id));
	}
	
	
	//getAll books in sorting & pagination manner
	@GetMapping("/books")
	public ResponseEntity<List<bookDto>> getAllBooks(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "3") int size,@RequestParam(defaultValue = "author") String sortby)
	{
	return ResponseEntity.ok(bookservice.getAllBooks(page, size, sortby));
	}
	
	//Update Book 
	@PutMapping("/updatebook/{id}")
	public ResponseEntity<bookDto> updateBook(@PathVariable Long id,@RequestBody bookDto bookdto)
	{
		return ResponseEntity.ok(bookservice.updateBook(id, bookdto));	
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteBook(@PathVariable Long id)
	{
		bookservice.deleteBook(id);
		return ResponseEntity.ok("Book Deleted Sucessfully");
	}
	
	@GetMapping("/bykeyword")
	public List<bookDto> getBookByKeyword(@RequestParam String keyword)
	{
		return bookservice.searchByKeyword(keyword);
	}
	
	
	
	@PatchMapping("/updatebookfield/{id}")
	public ResponseEntity<Map<String, Object>> updateBookByField(@PathVariable Long id,@RequestParam() String fieldName,@RequestParam String newValue)
	{
		bookDto bookdto=bookservice.updateBookField(id, fieldName, newValue);
		
		Map<String, Object> map=new HashMap<>();
		map.put("Message","Book Updated Successfully....!!!");
		map.put("String", bookdto);
		
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
}
