package com.ford.poc.controller;

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
	public IncentiveStructureBO saveIncentiveStructure(@RequestBody IncentiveStructure incStructure) {
		IncentiveStructure incStructureEO = null;
		IncentiveStructureBO response = new IncentiveStructureBO();
		try {
			incStructureEO = incentiveService.saveIncentiveStructure(incStructure);
			if (incStructureEO != null) {
				incentiveHelper.convertIncentiveStructureEoToBo(incStructureEO, response);
			}
		} catch (Exception e) {
			response.setStatus("Failure");
			response.setStatusMsg(e.getMessage());
		}
		return response;
	}

	@GetMapping("/getAllIncentiveStructure/{programCode}/{productType}")
	public List<IncentiveStructureBO> getAllIncentiveStructure(@PathVariable("programCode") String programCode,
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
