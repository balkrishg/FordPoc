package com.ford.poc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ford.poc.eo.IncentiveContractSales;
import com.ford.poc.eo.IncentiveContractSalesCancellation;
import com.ford.poc.eo.IncentiveProgram;
import com.ford.poc.eo.IncentiveStructure;
import com.ford.poc.repository.IncentiveContractSalesCancellationRepository;
import com.ford.poc.repository.IncentiveContractSalesRepository;
import com.ford.poc.repository.IncentiveProgramRepository;
import com.ford.poc.repository.IncentiveStructureRepository;

@Service
public class IncentiveServiceImpl implements IncentiveService {

	@Autowired
	IncentiveProgramRepository incentiveProgramRepository;

	@Autowired
	IncentiveStructureRepository incentiveStructureRepository;
	
	@Autowired
	IncentiveContractSalesRepository incentiveContractSalesRepository;
	
	@Autowired
	IncentiveContractSalesCancellationRepository incentiveContractSalesCancellationRepository;

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
	public List<IncentiveStructure> getAllIncentiveStructureByProgramCode(String programCode) {
		return incentiveStructureRepository.findByProgramCode(programCode);
	}
	
	@Override
	public List<IncentiveStructure> getAllIncentiveStructure(String programCode, String productType) {
		return incentiveStructureRepository.findByProgramCodeAndProductType(programCode, productType);
	}

	@Override
	public List<IncentiveContractSales> getData(String programCode) throws Exception {
		IncentiveProgram incProgram = incentiveProgramRepository.findById(programCode).orElseThrow(() -> new Exception("Invalid Program Code : " + programCode));
		System.out.println("#######IncentiveProgram###### From Date"+incProgram.getDateFrom()+"To Date"+incProgram.getDateTo());
		List<IncentiveContractSales>  incentiveContractSales = incentiveContractSalesRepository.findByEffectiveDateAndExpiryDate(incProgram.getDateFrom(), incProgram.getDateTo());
		List<IncentiveContractSalesCancellation> incentiveContractSalesCancellation = incentiveContractSalesCancellationRepository.findByEffectiveDateAndExpiryDate(incProgram.getDateFrom(), incProgram.getDateTo());
		System.out.println("!!!!!!!!!!!!Cancellation insert!!!!!!!!111111"+incentiveContractSalesCancellation);
		return incentiveContractSalesRepository.findByEffectiveDateAndExpiryDate(incProgram.getDateFrom(), incProgram.getDateTo());
	}

	

}
