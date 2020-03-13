package com.ford.poc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ford.poc.eo.IncentiveProgram;
import com.ford.poc.repository.IncentiveProgramRepository;

@Service
public class IncentiveServiceImpl implements IncentiveService {

	@Autowired
	IncentiveProgramRepository incentiveProgramRepository;

	@Override
	public String saveIncentiveProgram(IncentiveProgram incProgram) {

		String status = "";
		if(incentiveProgramRepository.save(incProgram) != null) {
		 	status = "Success";
		}else {
			status = "Failed";
		}
		return status;
	}

	@Override
	public List<IncentiveProgram> getAllIncentiveProgram() {
		return incentiveProgramRepository.findAll();		
	}

}
