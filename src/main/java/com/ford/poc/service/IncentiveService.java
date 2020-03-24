package com.ford.poc.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ford.poc.eo.IncentiveCalculation;
import com.ford.poc.eo.IncentiveDealerTarget;
import com.ford.poc.eo.IncentiveProgram;
import com.ford.poc.eo.IncentiveStructure;

@Service
public interface IncentiveService {

	public IncentiveProgram saveIncentiveProgram(IncentiveProgram incProgram);

	public List<IncentiveProgram> getAllIncentiveProgram();

	public IncentiveProgram getIncentiveProgram(String programCode) throws Exception;

//	public List<IncentiveProgram> getIncentiveProgramByTargetMonth(String targetMonth) throws ParseException;

	public IncentiveStructure saveIncentiveStructure(IncentiveStructure incStructure);

	public List<IncentiveStructure> getAllIncentiveStructureByProgramCode(String programCode);

	public List<IncentiveStructure> getAllIncentiveStructure(String programCode, String productType);

	public List<IncentiveCalculation> calculateIncentiveForParticularDealer(String dealerCode) throws ParseException;

	public List<IncentiveDealerTarget> getAllDealerCodes();

	public IncentiveDealerTarget getDealerTarget(String dealerCode);
}
