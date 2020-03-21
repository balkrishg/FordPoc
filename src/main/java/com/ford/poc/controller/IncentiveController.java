package com.ford.poc.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ford.poc.bo.IncentiveProgramBO;
import com.ford.poc.bo.IncentiveStructureBO;
import com.ford.poc.eo.IncentiveProgram;
import com.ford.poc.eo.IncentiveStructure;
import com.ford.poc.helper.IncentiveHelper;
import com.ford.poc.service.IncentiveService;

@RestController
@RequestMapping("/api/ford/poc")
public class IncentiveController {

	@Autowired
	private IncentiveService incentiveService;

	@Autowired
	private IncentiveHelper incentiveHelper;

	@PostMapping("/saveIncentiveProgram")
	public String saveIncentiveProgram(@RequestBody IncentiveProgramBO request) throws ParseException {
		IncentiveProgram incProgram = new IncentiveProgram();
		incentiveHelper.convertIncentiveProgramBoToEo(incProgram, request);
		return incentiveService.saveIncentiveProgram(incProgram);
	}

	@GetMapping("/getAllIncentiveProgram")
	public List<IncentiveProgram> getAllIncentiveProgram() {
		return incentiveService.getAllIncentiveProgram();
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
			response.setStatus("Failure");
			response.setStatusMsg(e.getMessage());
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
	public List<IncentiveStructureBO> getAllIncentiveStructureByProgramCode(@PathVariable("programCode") String programCode) {
		List<IncentiveStructure> incStructureList = null;
		List<IncentiveStructureBO> response = new ArrayList<IncentiveStructureBO>();
		try {
			incStructureList = incentiveService.getAllIncentiveStructureByProgramCode(programCode);
			if(incStructureList !=null) {
				incentiveHelper.convertIncentiveStructureEoListToBoList(incStructureList, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@GetMapping("/getAllIncentiveStructure/{programCode}/{productType}")
	public List<IncentiveStructureBO> getAllIncentiveStructureByProgramCodeAndProductType(@PathVariable("programCode") String programCode,
			@PathVariable("productType") String productType) {
		List<IncentiveStructure> incStructureList = null;
		List<IncentiveStructureBO> response = new ArrayList<IncentiveStructureBO>();
		try {
			incStructureList = incentiveService.getAllIncentiveStructure(programCode,productType);
			if(incStructureList !=null) {
				incentiveHelper.convertIncentiveStructureEoListToBoList(incStructureList, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

}
