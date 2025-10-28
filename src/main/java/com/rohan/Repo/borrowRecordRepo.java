package com.rohan.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rohan.Entity.borrowRecordEntity;

public interface borrowRecordRepo extends JpaRepository<borrowRecordEntity, Long> {
	
	List<borrowRecordEntity> findByMemberId(Long memberId);

}
