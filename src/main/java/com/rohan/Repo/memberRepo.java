package com.rohan.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rohan.Entity.memberEntity;

public interface memberRepo extends JpaRepository<memberEntity, Long>{
	
	List<memberEntity> findByNameContainingIgnoreCase(String name);
	

}
