package com.ford.poc.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ford.poc.bo.IncentiveCalculationReportRequestBO;
import com.ford.poc.bo.IncentiveDealerCodesBO;
import com.ford.poc.bo.IncentiveProgramBO;
import com.ford.poc.bo.IncentiveStructureBO;
import com.ford.poc.bo.IncentiveStructureListBO;
import com.ford.poc.eo.IncentiveCalculation;
import com.ford.poc.eo.IncentiveDealerDetails;
import com.ford.poc.eo.IncentiveProgram;
import com.ford.poc.eo.IncentiveStructure;
import com.ford.poc.helper.IncentiveHelper;
import com.ford.poc.service.IncentiveService;

@RestController
@CrossOrigin(origins = { "*", })
@RequestMapping("/api/ford/poc")
public class IncentiveController {

	@Autowired
	private IncentiveService incentiveService;

	@Autowired
	private IncentiveHelper incentiveHelper;
	
	Log log  = LogFactory.getLog("IncentiveController");

	@PostMapping("/saveIncentiveProgram")
	public IncentiveProgramBO saveIncentiveProgram(@RequestBody IncentiveProgramBO request) throws ParseException {

		IncentiveProgram incProgram = null;
		IncentiveProgramBO response = new IncentiveProgramBO();
		try {
			IncentiveProgram incProgramEO = new IncentiveProgram();
			incentiveHelper.convertIncentiveProgramBoToEo(incProgramEO, request);
			incProgram = incentiveService.saveIncentiveProgram(incProgramEO);
			if (incProgram != null) {
				incentiveHelper.convertIncentiveProgramEoToBo(incProgram, response);
			}
		} catch (Exception e) {
			response.setStatus("Failure");
			response.setStatusMsg(e.getMessage());
		}
		return response;
	}

	@GetMapping("/getAllIncentiveProgram")
	public List<IncentiveProgramBO> getAllIncentiveProgram() {
		List<IncentiveProgramBO> incentiveProgramBOList = new ArrayList<IncentiveProgramBO>();
		incentiveHelper.convertIncentiveProgramEoListToBoList(incentiveService.getAllIncentiveProgram(),
				incentiveProgramBOList);
		return incentiveProgramBOList;

	}

	@GetMapping("/getIncentiveProgram/{programCode}")
	public IncentiveProgramBO getIncentiveProgram(@PathVariable("programCode") String programCode) {
		IncentiveProgram incProgram = null;
		IncentiveProgramBO response = new IncentiveProgramBO();
		try {
			incProgram = incentiveService.getIncentiveProgram(programCode);
			if (incProgram != null) {
				incentiveHelper.convertIncentiveProgramEoToBo(incProgram, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@PostMapping("/saveIncentiveStructure")
	public IncentiveStructureBO saveIncentiveStructure(@RequestBody IncentiveStructureBO request) {
		IncentiveStructure incStructure = null;
		IncentiveStructureBO response = new IncentiveStructureBO();
		try {
			IncentiveStructure incStructureEO = new IncentiveStructure();
			incentiveHelper.convertIncentiveStructureBoToEo(incStructureEO, request);
			incStructure = incentiveService.saveIncentiveStructure(incStructureEO);
			if (incStructure != null) {
				incentiveHelper.convertIncentiveStructureEoToBo(incStructure, response);
			}
		} catch (Exception e) {
			response.setStatus("Failure");
			response.setStatusMsg(e.getMessage());
		}
		return response;
	}

	@GetMapping("/getAllIncentiveStructureByProgramCode/{programCode}")
	public IncentiveStructureListBO getAllIncentiveStructureByProgramCode(
			@PathVariable("programCode") String programCode) {
		IncentiveProgram incProgram = null;
		IncentiveProgramBO incProgramBO = new IncentiveProgramBO();
		List<IncentiveStructure> incStructureList = null;
		List<IncentiveStructureBO> incStructureBOList = new ArrayList<IncentiveStructureBO>();
		IncentiveStructureListBO response = new IncentiveStructureListBO();
		try {
			incProgram = incentiveService.getIncentiveProgram(programCode);
			if (incProgram != null) {
				incentiveHelper.convertIncentiveProgramEoToBo(incProgram, incProgramBO);
			}
			incStructureList = incentiveService.getAllIncentiveStructureByProgramCode(programCode);
			if (incStructureList != null) {
				incentiveHelper.convertIncentiveStructureEoListToBoList(incStructureList, incStructureBOList);
			}
			response.setProgramCode(incProgramBO.getProgramCode());
			response.setProgramName(incProgramBO.getProgramName());
			response.setDateFrom(incProgramBO.getDateFrom());
			response.setDateTo(incProgramBO.getDateTo());
			response.setIncentiveStructureBOList(incStructureBOList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@GetMapping("/getAllIncentiveStructure/{programCode}/{productType}")
	public List<IncentiveStructureBO> getAllIncentiveStructureByProgramCodeAndProductType(
			@PathVariable("programCode") String programCode, @PathVariable("productType") String productType) {
		List<IncentiveStructure> incStructureList = null;
		List<IncentiveStructureBO> response = new ArrayList<IncentiveStructureBO>();
		try {
			incStructureList = incentiveService.getAllIncentiveStructure(programCode, productType);
			if (incStructureList != null) {
				incentiveHelper.convertIncentiveStructureEoListToBoList(incStructureList, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@GetMapping("/getDealerCodes")
	public List<IncentiveDealerCodesBO> getDealerCodes() throws Exception {
		List<IncentiveDealerDetails> incDealerDetailsList = incentiveService.getAllDealerCodes();
		List<IncentiveDealerCodesBO> incDealerCodes = new ArrayList<IncentiveDealerCodesBO>();
		for (IncentiveDealerDetails incDealerTarget : incDealerDetailsList) {
			IncentiveDealerCodesBO incDealerCode = new IncentiveDealerCodesBO();
			incDealerCode.setLabel(incDealerTarget.getDealerName());
			incDealerCode.setValue(incDealerTarget.getDealerCode());
			incDealerCodes.add(incDealerCode);
		}
		return incDealerCodes;
	}

	@PostMapping("/calculateIncentive")
	public List<IncentiveCalculation> calculateIncentive(@RequestBody List<String> dealerCodes) throws Exception {
		List<IncentiveCalculation> incCalculation = new ArrayList<IncentiveCalculation>();
			for(String incDealer: dealerCodes) {
					incCalculation.addAll(incentiveService.calculateIncentiveForParticularDealer(incDealer));
					incentiveService.saveIncentiveCalculationList(incCalculation);
		}
		return incCalculation;
	}

	@GetMapping("/calculateIncentiveForAllDealers")
	public String calculateIncentiveForAllDealers() throws Exception {
		List<IncentiveCalculation> incCalculationList = new ArrayList<IncentiveCalculation>();
		List<IncentiveDealerDetails> incDealerDetailsList = incentiveService.getAllDealerCodes();
		for(IncentiveDealerDetails incDealer: incDealerDetailsList) {
			  incCalculationList.addAll(incentiveService.calculateIncentiveForParticularDealer(incDealer.getDealerCode()));
			  incentiveService.saveIncentiveCalculationList(incCalculationList); 
		}
		return "Success";
	}

	@PostMapping("/getIncentiveCalculationReport")
	public Map<String, List<IncentiveCalculation>> getIncentiveCalculationReport(
			@RequestBody IncentiveCalculationReportRequestBO reportRequest) throws Exception {

		return incentiveService.getIncentiveCalculationList(reportRequest.getDealerCodes(),
				reportRequest.getProgramCodes(), reportRequest.getIncentiveFrom(), reportRequest.getIncentiveTo());
	}

}
