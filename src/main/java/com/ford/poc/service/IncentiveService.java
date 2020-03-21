package com.ford.poc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ford.poc.eo.IncentiveContractSales;
import com.ford.poc.eo.IncentiveProgram;
import com.ford.poc.eo.IncentiveStructure;

@Service
public interface IncentiveService {

	public String saveIncentiveProgram(IncentiveProgram incProgram);
	
	public List<IncentiveProgram> getAllIncentiveProgram();
	
	public IncentiveProgram getIncentiveProgram(String programCode) throws Exception;
	
	public IncentiveStructure saveIncentiveStructure(IncentiveStructure incStructure);
	
	public List<IncentiveStructure> getAllIncentiveStructureByProgramCode(String programCode);

	public List<IncentiveStructure> getAllIncentiveStructure(String programCode, String productType);

	public List<IncentiveContractSales> getData(String programCode) throws Exception;
}
