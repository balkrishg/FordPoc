package com.ford.poc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ford.poc.service.FordPocIncentiveServiceImpl;

@RestController
@RequestMapping("/api/ford/poc")
public class FordPocIncentiveController {
	
	private FordPocIncentiveServiceImpl fordPocIncentiveService;
	
	@Autowired
	public FordPocIncentiveController(FordPocIncentiveServiceImpl fordPocIncentiveService){
		this.fordPocIncentiveService = fordPocIncentiveService;
	}
	
	@PostMapping("/saveIncetiveProgram")
	public String saveIncetiveProgram(@RequestBody String v) {
		return null;
		
	}

}
