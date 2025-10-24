package com.rohan.Mapper;

import com.rohan.Dto.memberDto;
import com.rohan.Entity.memberEntity;

public class memberMapper {
	
	public static memberDto toDto(memberEntity memberentity)
	{
	
		memberDto memberdto=new memberDto();
		memberdto.setEmail(memberentity.getEmail());
		memberdto.setId(memberentity.getId());
		memberdto.setMembershipType(memberentity.getMembershipType());
		memberdto.setName(memberentity.getName());
		
		return memberdto;
		
	}
	
	public static memberEntity toEntity(memberDto memberDto)
	{
		memberEntity entity =new memberEntity();
		entity.setId(memberDto.getId());
		entity.setEmail(memberDto.getEmail());
		entity.setName(memberDto.getName());
		entity.setMembershipType(memberDto.getMembershipType()); 
		return entity;
	}

	
	 
}
