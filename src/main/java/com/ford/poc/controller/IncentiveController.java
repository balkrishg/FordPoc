package com.ford.poc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ford.poc.eo.IncentiveProgram;
import com.ford.poc.service.IncentiveService;

@RestController
@RequestMapping("/api/ford/poc")
public class IncentiveController {

	@Autowired
	private IncentiveService incentiveService;

	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}

	@PostMapping("/saveIncentiveProgram")
	public String saveIncentiveProgram(@RequestBody IncentiveProgram incProgram) {
		return incentiveService.saveIncentiveProgram(incProgram);
	}

	@GetMapping("/getAllIncentiveProgram")
	public List<IncentiveProgram> getAllIncentiveProgram() {
		return incentiveService.getAllIncentiveProgram();
	}

}
