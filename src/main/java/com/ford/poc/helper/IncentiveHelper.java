package com.ford.poc.helper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ford.poc.bo.IncentiveContractBO;
import com.ford.poc.bo.IncentiveProgramBO;
import com.ford.poc.bo.IncentiveStructureBO;
import com.ford.poc.eo.IncentiveContractSales;
import com.ford.poc.eo.IncentiveContractSalesCancellation;
import com.ford.poc.eo.IncentiveProgram;
import com.ford.poc.eo.IncentiveStructure;

@Service
public class IncentiveHelper {

	DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

	public void convertIncentiveProgramBoToEo(IncentiveProgram incProgram, IncentiveProgramBO request)
			throws ParseException {
		incProgram.setProgramCode(request.getProgramCode());
		incProgram.setProgramName(request.getProgramName());
		incProgram.setDateFrom(formatter.parse(request.getDateFrom()));
		incProgram.setDateTo(formatter.parse(request.getDateTo()));
	}

	public void convertIncentiveProgramEoToBo(IncentiveProgram incProgram, IncentiveProgramBO response) {
		response.setProgramCode(incProgram.getProgramCode());
		response.setProgramName(incProgram.getProgramName());
		response.setDateFrom(formatter.format(incProgram.getDateFrom()));
		response.setDateTo(formatter.format(incProgram.getDateTo()));
		response.setStatus("Success");
	}

	public void convertIncentiveProgramEoListToBoList(List<IncentiveProgram> incProgramList,
			List<IncentiveProgramBO> responseList) {
		if (incProgramList != null) {
			for (IncentiveProgram incProgram : incProgramList) {
				IncentiveProgramBO bo = new IncentiveProgramBO();
				bo.setProgramCode(incProgram.getProgramCode());
				bo.setProgramName(incProgram.getProgramName());
				bo.setDateFrom(formatter.format(incProgram.getDateFrom()));
				bo.setDateTo(formatter.format(incProgram.getDateTo()));
				responseList.add(bo);
			}
		}
	}

	public void convertIncentiveStructureBoToEo(IncentiveStructure incStructure, IncentiveStructureBO request)
			throws ParseException {
		incStructure.setIncStructureId(
				request.getIncStructureId() != null ? Long.parseLong(request.getIncStructureId()) : null);
		incStructure.setProgramCode(request.getProgramCode());
		incStructure.setProgramName(request.getProgramName());
		incStructure.setDateFrom(formatter.parse(request.getDateFrom()));
		incStructure.setDateTo(formatter.parse(request.getDateTo()));
		incStructure.setProductType(request.getProductType());
		incStructure.setSubProductType(request.getSubProductType());
		incStructure.setProductSaleType(request.getProductSaleType());
		incStructure.setContractType(request.getContractType());
		incStructure.setRecipient(request.getRecipient());
		incStructure.setNoOfServices(Integer.parseInt(request.getNoOfServices()));
		incStructure.setPerformanceTarget(request.getPerformanceTarget());
		incStructure.setIncentives(Integer.parseInt(request.getIncentives()));
	}

	public void convertIncentiveStructureEoToBo(IncentiveStructure incStructure, IncentiveStructureBO response) {
		response.setIncStructureId(incStructure.getIncStructureId().toString());
		response.setProgramCode(incStructure.getProgramCode());
		response.setProgramName(incStructure.getProgramName());
		response.setDateFrom(formatter.format(incStructure.getDateFrom()));
		response.setDateTo(formatter.format(incStructure.getDateTo()));
		response.setProductType(incStructure.getProductType());
		response.setSubProductType(incStructure.getSubProductType());
		response.setProductSaleType(incStructure.getProductSaleType());
		response.setContractType(incStructure.getContractType());
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
				bo.setDateFrom(formatter.format(incStructure.getDateFrom()));
				bo.setDateTo(formatter.format(incStructure.getDateTo()));
				bo.setProductType(incStructure.getProductType());
				bo.setSubProductType(incStructure.getSubProductType());
				bo.setProductSaleType(incStructure.getProductSaleType());
				bo.setContractType(incStructure.getContractType());
				bo.setRecipient(incStructure.getRecipient());
				bo.setNoOfServices(incStructure.getNoOfServices().toString());
				bo.setPerformanceTarget(incStructure.getPerformanceTarget());
				bo.setIncentives(incStructure.getIncentives().toString());
				responseList.add(bo);
			}
		}
	}

	public List<IncentiveContractBO> convertIncentiveContractSalesEoToBo(
			List<IncentiveContractSales> incContractSalesList) {
		List<IncentiveContractBO> incContractList = new ArrayList<IncentiveContractBO>();
		if (incContractSalesList != null) {
			for (IncentiveContractSales incContractSales : incContractSalesList) {
				IncentiveContractBO bo = new IncentiveContractBO();
				bo.setDealerCode(incContractSales.getDealerCode());
				bo.setProductType(incContractSales.getProductType());
				bo.setSubProductType(incContractSales.getSubProductType());
				bo.setProductSaleType(incContractSales.getProductSaleType());
				bo.setNoOfClaimsAllowed(incContractSales.getNoOfClaimsAllowed());
				bo.setProductSaleType(incContractSales.getProductSaleType());
				bo.setContractType(incContractSales.getContractType());
				bo.setContractStatus(incContractSales.getContractStatus());
				bo.setContractRegistrationDate(incContractSales.getContractRegistrationDate());
				incContractList.add(bo);
			}
		}
		return incContractList;
	}

	public List<IncentiveContractBO> convertIncentiveContractSalesCancellationEoToBo(
			List<IncentiveContractSalesCancellation> incContractSalesCancellationList) {
		List<IncentiveContractBO> incContractList = new ArrayList<IncentiveContractBO>();
		if (incContractSalesCancellationList != null) {
			for (IncentiveContractSalesCancellation incContractSalesCancellation : incContractSalesCancellationList) {
				IncentiveContractBO bo = new IncentiveContractBO();
				bo.setDealerCode(incContractSalesCancellation.getDealerCode());
				bo.setProductType(incContractSalesCancellation.getProductType());
				bo.setSubProductType(incContractSalesCancellation.getSubProductType());
				bo.setProductSaleType(incContractSalesCancellation.getProductSaleType());
				bo.setNoOfClaimsAllowed(incContractSalesCancellation.getNoOfClaimsAllowed());
				bo.setProductSaleType(incContractSalesCancellation.getProductSaleType());
				bo.setContractType(incContractSalesCancellation.getContractType());
				bo.setContractStatus(incContractSalesCancellation.getContractStatus());
				bo.setContractRegistrationDate(incContractSalesCancellation.getContractRegistrationDate());
				incContractList.add(bo);
			}
		}
		return incContractList;
	}

}
