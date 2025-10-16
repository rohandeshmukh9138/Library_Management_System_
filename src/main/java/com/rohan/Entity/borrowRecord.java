package com.rohan.Entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class borrowRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDate borrowDate;
	
	private LocalDate returnDate;
	
	private String status;
	
	@ManyToOne
	private bookEntity book;
	
	@ManyToOne
	private memberEntity member;
	
}
