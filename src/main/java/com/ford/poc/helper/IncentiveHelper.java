package com.ford.poc.helper;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ford.poc.bo.IncentiveProgramBO;
import com.ford.poc.bo.IncentiveStructureBO;
import com.ford.poc.eo.IncentiveProgram;
import com.ford.poc.eo.IncentiveStructure;

@Service
public class IncentiveHelper {

	public void convertIncentiveProgramEoToBo(IncentiveProgram incProgram, IncentiveProgramBO response) {
		response.setProgramCode(incProgram.getProgramCode());
		response.setProgramName(incProgram.getProgramName());
		response.setScheduleService(incProgram.getScheduleService());
		response.setStandardSSP(incProgram.getStandardSSP());
		response.setFreeSSP(incProgram.getFreeSSP());
		response.setFlexiSSP(incProgram.getFlexiSSP());
		response.setFlexiEW(incProgram.getFlexiEW());
		response.setStatus("Success");
	}

	public void convertIncentiveStructureBoToEo(IncentiveStructure incStructure, IncentiveStructureBO request) {
		incStructure.setIncStructureId(
				request.getIncStructureId() != null ? Long.parseLong(request.getIncStructureId()) : null);
		incStructure.setProgramCode(request.getProgramCode());
		incStructure.setProgramName(request.getProgramName());
		incStructure.setProductType(request.getProductType());
		incStructure.setSubProductType(request.getSubProductType());
		incStructure.setProductSaleType(request.getProductSaleType());
		incStructure.setServiceType(request.getServiceType());
		incStructure.setRecipient(request.getRecipient());
		incStructure.setNoOfServices(Integer.parseInt(request.getNoOfServices()));
		incStructure.setPerformanceTarget(request.getPerformanceTarget());
		incStructure.setIncentives(Integer.parseInt(request.getIncentives()));
	}

	public void convertIncentiveStructureEoToBo(IncentiveStructure incStructure, IncentiveStructureBO response) {
		response.setIncStructureId(incStructure.getIncStructureId().toString());
		response.setProgramCode(incStructure.getProgramCode());
		response.setProgramName(incStructure.getProgramName());
		response.setProductType(incStructure.getProductType());
		response.setSubProductType(incStructure.getSubProductType());
		response.setProductSaleType(incStructure.getProductSaleType());
		response.setServiceType(incStructure.getServiceType());
		response.setRecipient(incStructure.getRecipient());
		response.setNoOfServices(incStructure.getNoOfServices().toString());
		response.setPerformanceTarget(incStructure.getPerformanceTarget());
		response.setIncentives(incStructure.getIncentives().toString());
		response.setStatus("Success");
	}

	public void convertIncentiveStructureEoListToBoList(List<IncentiveStructure> incStructureList,
			List<IncentiveStructureBO> responseList) {
		if (incStructureList != null) {
			for (IncentiveStructure incStructure : incStructureList) {
				IncentiveStructureBO bo = new IncentiveStructureBO();
				bo.setIncStructureId(incStructure.getIncStructureId().toString());
				bo.setProgramCode(incStructure.getProgramCode());
				bo.setProgramName(incStructure.getProgramName());
				bo.setProductType(incStructure.getProductType());
				bo.setSubProductType(incStructure.getSubProductType());
				bo.setProductSaleType(incStructure.getProductSaleType());
				bo.setServiceType(incStructure.getServiceType());
				bo.setRecipient(incStructure.getRecipient());
				bo.setNoOfServices(incStructure.getNoOfServices().toString());
				bo.setPerformanceTarget(incStructure.getPerformanceTarget());
				bo.setIncentives(incStructure.getIncentives().toString());
				responseList.add(bo);
			}
		}
	}

}
