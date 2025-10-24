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
	
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<memberDto> getMemberById(@PathVariable Long id)
	{
		return ResponseEntity.ok(memberservice.getMemberById(id));
	}
	
	
	@GetMapping("/getAll")
	public ResponseEntity<List<memberDto>> getAllmember(@RequestParam(value = "pageNumber",defaultValue = "0")int pageNumber,@RequestParam(value = "pageSize",defaultValue = "5") int pageSize)
	{
		return ResponseEntity.ok(memberservice.getAllmember(pageNumber, pageSize));
	}
	
	
	
	@PutMapping("/updateMember/{id}")
	public ResponseEntity<memberDto> updateMember(@PathVariable Long id,@RequestBody memberDto memberDto)
	{
		return new ResponseEntity<memberDto>(memberservice.updateMember(id, memberDto),HttpStatus.OK);
	}

	
	@PatchMapping("/updatememberfield/{id}")
	public ResponseEntity<Map<String, Object>> updateMemberField(@PathVariable Long id,@RequestParam String fieldName,@RequestParam String newValue)
	{
		memberDto updateMemberField = memberservice.updateMemberField(id, fieldName, newValue);
		
		Map<String, Object> map=new HashMap<>();
		map.put("Message", "Member Updated Successfully");
		map.put("Data", updateMemberField);
		
		return ResponseEntity.ok(map);
		
		
	}






}


