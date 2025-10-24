package com.rohan.Service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rohan.Dto.bookDto;
import com.rohan.Entity.bookEntity;
import com.rohan.Mapper.bookMapper;
import com.rohan.Repo.bookRepo;

@Service
public class bookService {

	@Autowired
	bookRepo bookrepo;
	
	
	//add book 
	public bookDto addBook(bookDto bookdto)
	{
		bookEntity bookentity = bookMapper.toEntity(bookdto);
		bookEntity save = bookrepo.save(bookentity);
		return bookMapper.toDto(save);
	}
	
	//get book by ID
	public bookDto getBook(Long id)
	{
		bookEntity entity=bookrepo.findById(id).orElseThrow(()-> new RuntimeException("Book not found exception"));
		return bookMapper.toDto(entity);
	}
	
	//get all book in sorting & pagination manner
	public List<bookDto> getAllBooks(int page,int size,String sortby)
	{
		Pageable pageable=PageRequest.of(page, size, Sort.by(sortby));
		Page<bookEntity> all = bookrepo.findAll(pageable);
		return all.stream().map(bookMapper::toDto).collect(Collectors.toList());
	}
	
	//updateBook
	public bookDto updateBook(Long id,bookDto bookDto)
	{
		bookEntity bookentity= bookrepo.findById(id).orElseThrow(()->new RuntimeException("Book not found with ID"+ id));
		bookentity.setAuthor(bookDto.getAuthor());
		bookentity.setAvailableCopies(bookDto.getAvailableCopies());
		bookentity.setTitle(bookDto.getTitle());
		bookentity.setTotalCopies(bookDto.getTotalCopies());
		
		return bookMapper.toDto(bookrepo.save(bookentity));
	}
	
	//delete book
	public void deleteBook(Long id)
	{
		if(!bookrepo.existsById(id))
		{
			throw new RuntimeException("Book not found with ID "+ id);
		}
		bookrepo.deleteById(id);
	}
	
	//search book by keyword
	public List<bookDto> searchByKeyword(String keyword)
	{
		List<bookEntity> books= bookrepo.findByAuthorContainingIgnoreCaseOrTitleContainingIgnoreCase(keyword,keyword);
		return books.stream().map(bookMapper::toDto).collect(Collectors.toList());
	}
	
	
	public bookDto updateBookField(Long id,String fieldName,String newValue)
	{
	 
		bookEntity bookentity=bookrepo.findById(id).orElseThrow(()->new RuntimeException("Book not Found with id" +id));
		
		switch(fieldName)
		{
		case "title":
			bookentity.setTitle(newValue);
			break;
			
		case "author":
			bookentity.setAuthor(newValue);
			break;
			
		case "totalCopies":
			try
			{
				int totalcopies=Integer.parseInt(newValue);
				bookentity.setTotalCopies(totalcopies);
			}catch(Exception e)
			{
				throw new RuntimeException("Invalid value for totalcopies it should be number");
			}
			break;
			
		case "availableCopies":
			try
			{
				int availableCopies=Integer.parseInt(newValue);
				bookentity.setAvailableCopies(availableCopies);
			}catch(Exception e)
			{
				throw new RuntimeException("Invalid number for availableCopies it should be number");
			}
			break;
			
			default:
				throw new RuntimeException("Invalid field please give the proper Field");
				
		}
		return bookMapper.toDto(bookrepo.save(bookentity));
	}
	
	
}
