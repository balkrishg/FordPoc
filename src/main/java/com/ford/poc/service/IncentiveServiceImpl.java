package com.ford.poc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ford.poc.eo.IncentiveProgram;
import com.ford.poc.eo.IncentiveStructure;
import com.ford.poc.repository.IncentiveProgramRepository;
import com.ford.poc.repository.IncentiveStructureRepository;

@Service
public class IncentiveServiceImpl implements IncentiveService {

	@Autowired
	IncentiveProgramRepository incentiveProgramRepository;

	@Autowired
	IncentiveStructureRepository incentiveStructureRepository;

	@Override
	public String saveIncentiveProgram(IncentiveProgram incProgram) {

		String status = "";
		if (incentiveProgramRepository.save(incProgram) != null) {
			status = "Success";
		} else {
			status = "Failed";
		}
		return status;
	}

	@Override
	public List<IncentiveProgram> getAllIncentiveProgram() {
		return incentiveProgramRepository.findAll();
	}

	@Override
	public IncentiveProgram getIncentiveProgram(String programCode) throws Exception {
		IncentiveProgram incProgram = incentiveProgramRepository.findById(programCode)
				.orElseThrow(() -> new Exception("Invalid Program Code : " + programCode));
		return incProgram;
	}

	@Override
	public IncentiveStructure saveIncentiveStructure(IncentiveStructure incStructure) {
		return incentiveStructureRepository.save(incStructure);
	}

	@Override
	public List<IncentiveStructure> getAllIncentiveStructure(String programCode, String productType) {
		return incentiveStructureRepository.findByProgramCodeAndProductType(programCode, productType);
	}

}
