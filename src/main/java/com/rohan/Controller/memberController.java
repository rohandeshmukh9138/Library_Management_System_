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

import com.rohan.Dto.memberDto;
import com.rohan.Dto.memberResponseDto;
import com.rohan.Service.bookService;
import com.rohan.Service.memberService;

@RestController
public class memberController {
   
		
	@Autowired
	memberService memberservice;
	
	//to add Member
	@PostMapping("/addMember")
	public ResponseEntity<memberDto> addMember(@RequestBody memberDto memberdto)
	{
		return ResponseEntity.ok(memberservice.addMember(memberdto));
	}
	
	
	//to delete member
	@DeleteMapping("/deleteMember/{id}")
	public ResponseEntity<String> deleteById(@PathVariable Long id)
	{
		memberservice.deleteById(id);	
		return ResponseEntity.ok("Deleted Sucessfully..!!");
	}
	
	//get member bu ID
	@GetMapping("/getById/{id}")
	public ResponseEntity<memberDto> getMemberById(@PathVariable Long id)
	{
		return ResponseEntity.ok(memberservice.getMemberById(id));
	}
	
	//get All members in pagination manner
	@GetMapping("/getAllMembers")
	public ResponseEntity <memberResponseDto> getAllmember(@RequestParam(value = "pageNumber",defaultValue = "0")int pageNumber,@RequestParam(value = "pageSize",defaultValue = "3") int pageSize)
	{
		return ResponseEntity.ok(memberservice.getAllmember(pageNumber, pageSize));
	}
	
	
	//update member by ID
	@PutMapping("/updateMember/{id}")
	public ResponseEntity<memberDto> updateMember(@PathVariable Long id,@RequestBody memberDto memberDto)
	{
		return new ResponseEntity<memberDto>(memberservice.updateMember(id, memberDto),HttpStatus.OK);
	}

	//update member any field at run time
	@PatchMapping("/updatememberfield/{id}")
	public ResponseEntity<Map<String, Object>> updateMemberField(@PathVariable Long id,@RequestParam String fieldName,@RequestParam String newValue)
	{
		memberDto updateMemberField = memberservice.updateMemberField(id, fieldName, newValue);
		
		Map<String, Object> map=new HashMap<>();
		map.put("Message", "Member Updated Successfully");
		map.put("Data", updateMemberField);
		
		return ResponseEntity.ok(map);
		
		
	}
	
	
	//find member by name
	@GetMapping("/findMemberByName")
	public ResponseEntity<List<memberDto>> findMemberByName(@RequestParam String keyword)
	{
		List<memberDto> memberdto= memberservice.findMemberByName(keyword);
		return ResponseEntity.ok(memberdto);
	}


	//get recent added Members;
	@GetMapping("/getRecentAddedMembers")
	public ResponseEntity<List<memberDto>> getRecentAddedMembers()
	{
		return ResponseEntity.ok(memberservice.getRecentAddedMembers());
	}

}


