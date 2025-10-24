package com.rohan.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rohan.Entity.bookEntity;

public interface bookRepo extends JpaRepository<bookEntity, Long>{

	List<bookEntity> findByAuthorContainingIgnoreCaseOrTitleContainingIgnoreCase(String title,String author);
	
	
}
