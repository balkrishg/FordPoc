package com.ford.poc.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ford.poc.eo.IncentiveCalculation;
import com.ford.poc.eo.IncentiveDealerDetails;
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

	//public List<IncentiveCalculation> calculateIncentiveForParticularDealer(String dealerCode);

	public List<IncentiveDealerDetails> getAllDealerCodes();

	public IncentiveDealerTarget getDealerTarget(String dealerCode);

	public IncentiveDealerTarget getDealerTargetByMonth(String dealerCode, String dealerTargetMonth);

	public void saveIncentiveCalculationList(List<IncentiveCalculation> incCalculationList,
			IncentiveDealerTarget dealerTarget);

	public Map<String, List<IncentiveCalculation>> getIncentiveCalculationList(List<String> dealerCode,
			List<String> programCodes, String IncentiveFrom, String IncentiveTo);

	List<IncentiveCalculation> calculateIncentiveForParticularDealer(String dealerCode, String dealerTargetMonth);

	//public List<IncentiveDealerTarget> getDealerTargetByDealerCode(String dealerCode);
}
