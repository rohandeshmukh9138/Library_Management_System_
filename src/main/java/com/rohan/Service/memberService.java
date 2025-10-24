package com.rohan.Service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.rohan.Dto.memberDto;
import com.rohan.Entity.memberEntity;
import com.rohan.Mapper.memberMapper;
import com.rohan.Repo.memberRepo;
import org.springframework.data.domain.Pageable;



@Service
public class memberService {
	
	@Autowired
	memberRepo memberrepo;	

	//to add member
	public memberDto addMember(memberDto dto)
	{
		memberEntity entity = memberMapper.toEntity(dto);
		memberrepo.save(entity);
		return memberMapper.toDto(entity);
	}
	
	//to delete member
	public void deleteById(Long id)
	{
		if(!memberrepo.existsById(id))
		{
			throw new RuntimeException("Member not present with ID "+ id);
		}
		memberrepo.deleteById(id);
	}
	
	//to get member by id
	public memberDto getMemberById(Long id)
	{
		memberEntity memberentity=memberrepo.findById(id).orElseThrow(()->new RuntimeException("Member not found with the id"+ id));
		return memberMapper.toDto(memberentity);
	}
	
	//get all member in pagination format
	public List<memberDto> getAllmember(int pageNumber,int pageSize)
	{
		
	  Pageable p=PageRequest.of(pageNumber, pageSize);
	  
	  Page<memberEntity> memberentity= memberrepo.findAll(p);
	  
	  List<memberEntity> allmembers= memberentity.getContent();
	  
	  return allmembers.stream().map(memberMapper::toDto).collect(Collectors.toList());
	}
	
	
	
	
	
	
	//update the member by its ID
	public memberDto updateMember(Long id,memberDto memberDto)
	{
	memberEntity memberentity=memberrepo.findById(id).orElseThrow(()->new RuntimeException("Member not found to Update..!!"));
	memberentity.setName(memberDto.getName());
	memberentity.setMembershipType(memberDto.getMembershipType());
	memberentity.setEmail(memberDto.getEmail());
	
	return memberMapper.toDto(memberrepo.save(memberentity));
	}
	
	
	
	
	
	
	
	//Update anyfield at Runtime
	public memberDto updateMemberField(Long id,String fieldName,String newValue)
	{
		memberEntity memberentity=memberrepo.findById(id).orElseThrow(()->new RuntimeException("Member not found with that ID"+id));
		
		switch(fieldName)
		{
		case "name":
			  memberentity.setName(newValue);
			  break;
		
		case "email":
			  memberentity.setEmail(newValue);
			  break;
		
		case "membershipType":
			  memberentity.setMembershipType(newValue);
			  break;
			  
		default:
			throw new RuntimeException(fieldName+ "Field not found..!!");
			
			
		}
		memberrepo.save(memberentity);
		
		return memberMapper.toDto(memberentity);
	}
	
}
