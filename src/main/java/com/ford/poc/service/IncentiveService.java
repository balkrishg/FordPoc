package com.ford.poc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ford.poc.eo.IncentiveProgram;

@Service
public interface IncentiveService {

	public String saveIncentiveProgram(IncentiveProgram incProgram);
	
	public List<IncentiveProgram> getAllIncentiveProgram();
}
