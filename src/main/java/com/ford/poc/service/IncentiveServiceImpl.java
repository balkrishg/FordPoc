package com.ford.poc.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ford.poc.bo.IncentiveContractBO;
import com.ford.poc.eo.IncentiveCalculation;
import com.ford.poc.eo.IncentiveContractSales;
import com.ford.poc.eo.IncentiveContractSalesCancellation;
import com.ford.poc.eo.IncentiveDealerTarget;
import com.ford.poc.eo.IncentiveProgram;
import com.ford.poc.eo.IncentiveStructure;
import com.ford.poc.helper.IncentiveHelper;
import com.ford.poc.repository.IncentiveCalculationRepository;
import com.ford.poc.repository.IncentiveContractSalesCancellationRepository;
import com.ford.poc.repository.IncentiveContractSalesRepository;
import com.ford.poc.repository.IncentiveDealerTargetRepository;
import com.ford.poc.repository.IncentiveProgramRepository;
import com.ford.poc.repository.IncentiveStructureRepository;

@Service
public class IncentiveServiceImpl implements IncentiveService {

	@Autowired
	private IncentiveHelper incentiveHelper;

	@Autowired
	private IncentiveProgramRepository incentiveProgramRepository;

	@Autowired
	private IncentiveStructureRepository incentiveStructureRepository;

	@Autowired
	private IncentiveContractSalesRepository incentiveContractSalesRepository;

	@Autowired
	private IncentiveContractSalesCancellationRepository incentiveContractSalesCancellationRepository;

	@Autowired
	private IncentiveDealerTargetRepository incentiveDealerTargetRepository;

	@Autowired
	private IncentiveCalculationRepository incentiveCalculationRepository;

	DateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");

	@Override
	public IncentiveProgram saveIncentiveProgram(IncentiveProgram incProgram) {
		return incentiveProgramRepository.save(incProgram);
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

//	@Override
//	public List<IncentiveProgram> getIncentiveProgramByTargetMonth(String targetMonth) throws ParseException {
//		List<IncentiveProgram> incProgramList = new ArrayList<IncentiveProgram>();
//		incProgramList = incentiveProgramRepository.findByTargetMonth(targetMonth);
//		List<IncentiveProgram> filteredProgramList = new ArrayList<IncentiveProgram>();
//		String month = targetMonth.substring(0, 3);
//		String year = targetMonth.substring(3, 5);
//		for (IncentiveProgram incProgram : incProgramList) {
//			IncentiveProgram inc = new IncentiveProgram();
//			inc.setProgramCode(incProgram.getProgramCode());
//			inc.setProgramName(incProgram.getProgramName());
//			String dateFrom = formatter.format(incProgram.getDateFrom());
//			String dateTo = formatter.format(incProgram.getDateTo());
//			Date newDateFrom = null;
//			if (!dateFrom.toLowerCase().contains(month.toLowerCase())) {
//				dateFrom = "01-" + month + "-" + year;
//				newDateFrom = formatter.parse(dateFrom);
//				inc.setDateFrom(newDateFrom);
//			} else {
//				inc.setDateFrom(incProgram.getDateFrom());
//			}
//			if (!dateTo.toLowerCase().contains(month.toLowerCase())) {
//				Calendar cal = Calendar.getInstance();
//				if (newDateFrom != null) {
//					cal.setTime(newDateFrom);
//				} else {
//					cal.setTime(incProgram.getDateFrom());
//				}
//				cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
//				cal.getTime();
//			} else {
//				inc.setDateTo(incProgram.getDateTo());
//			}
//			filteredProgramList.add(inc);
//		}
//		for(IncentiveProgram incProgram : filteredProgramList) {
//			System.out.println("&&&&&&&&&&&&&&&&&&&&&");
//			System.out.println(incProgram.getProgramCode());
//			System.out.println(incProgram.getProgramName());
//			System.out.println(incProgram.getDateFrom());
//			System.out.println(incProgram.getDateTo());
//		}
//		return filteredProgramList;
//	}

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
	public List<IncentiveDealerTarget> getAllDealerCodes() {
		return incentiveDealerTargetRepository.findAll();
	}

	@Override
	public IncentiveDealerTarget getDealerTarget(String dealerCode) {
		return incentiveDealerTargetRepository.findByDealerCode(dealerCode);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IncentiveCalculation> calculateIncentiveForParticularDealer(String dealerCode) throws ParseException {
		IncentiveDealerTarget dealerTarget = getDealerTarget(dealerCode);
		List<IncentiveCalculation> incCalculationList = new ArrayList<IncentiveCalculation>();
		getAllIncentiveProgram().stream().forEach(incProgram -> {

			IncentiveCalculation incCalculationSSP = new IncentiveCalculation();
			IncentiveCalculation incCalculationOSP = new IncentiveCalculation();

			List<IncentiveStructure> incStructureList = getAllIncentiveStructureByProgramCode(
					incProgram.getProgramCode());
			List<IncentiveStructure> distinctIncStructureList = incStructureList.stream()
					.filter(distinctByKeys(IncentiveStructure::getProductType, IncentiveStructure::getSubProductType,
							IncentiveStructure::getContractType, IncentiveStructure::getNoOfServices))
					.collect(Collectors.toList());

			Set<String> incStructureSetKey = new HashSet<>();
			Set<String> productTypeSetKey = new HashSet<>();
			for (IncentiveStructure incStructure : distinctIncStructureList) {
				String incStructureKey = incStructure.getProductType() + incStructure.getSubProductType();
				String productTypeKey = incStructure.getProductType();
				incStructureSetKey.add(incStructureKey);
				productTypeSetKey.add(productTypeKey);
			}

			List<IncentiveContractSales> incContractSalesList = incentiveContractSalesRepository
					.findByDealerCode(incProgram.getDateFrom(), incProgram.getDateTo(), dealerCode);
			List<IncentiveContractSalesCancellation> incContractSalesCancellationList = incentiveContractSalesCancellationRepository
					.findByDealerCode(incProgram.getDateFrom(), incProgram.getDateTo(), dealerCode);

			List<IncentiveContractBO> incContractSalesBOList = new ArrayList<IncentiveContractBO>();
			List<IncentiveContractBO> incContractSalesCancellationBOList = new ArrayList<IncentiveContractBO>();
			incContractSalesBOList.addAll(incentiveHelper.convertIncentiveContractSalesEoToBo(incContractSalesList));
			incContractSalesCancellationBOList.addAll(
					incentiveHelper.convertIncentiveContractSalesCancellationEoToBo(incContractSalesCancellationList));

			for (IncentiveContractBO cancelledList : incContractSalesCancellationBOList) {
				for (IncentiveContractBO salesList : incContractSalesBOList) {
					if (cancelledList.equals(salesList)) {
						incContractSalesBOList.remove(cancelledList);
						break;
					}
				}
			}

			List<IncentiveContractBO> finalList = new ArrayList<IncentiveContractBO>();
			Map<String, Integer> noOfClaimsAllowedCount = new HashMap<String, Integer>();
			Map<String, Integer> salesTarget = new HashMap<String, Integer>();
			String noOfClaimsAllowedCountKey = null;
			String salesTargetKey = null;
			for (IncentiveStructure p : distinctIncStructureList) {
				for (IncentiveContractBO p1 : incContractSalesBOList) {
					if (p.getProductType().equalsIgnoreCase(p1.getProductType())
							&& p.getSubProductType().equalsIgnoreCase(p1.getSubProductType())
							&& p.getNoOfServices().equals(p1.getNoOfClaimsAllowed())
							&& p.getProductSaleType().equalsIgnoreCase(p1.getProductSaleType())) {
						if (p.getContractType().equals("ALL")) {
							if (p1.getContractType().equalsIgnoreCase("Service")
									|| p1.getContractType().equalsIgnoreCase("Sales")
									|| p1.getContractType().equalsIgnoreCase("N/A")) {
								finalList.add(p1);
								noOfClaimsAllowedCountKey = p.getProductType() + p.getSubProductType()
										+ p.getNoOfServices();
								utilMap(noOfClaimsAllowedCount, noOfClaimsAllowedCountKey);
								salesTargetKey = p1.getProductType() + p1.getSubProductType();
								utilMap(salesTarget, salesTargetKey);
							}
						} else if (p.getContractType().equalsIgnoreCase(p1.getContractType())) {
							finalList.add(p1);
							noOfClaimsAllowedCountKey = p.getProductType() + p.getSubProductType()
									+ p.getNoOfServices();
							utilMap(noOfClaimsAllowedCount, noOfClaimsAllowedCountKey);
							salesTargetKey = p1.getProductType() + p1.getSubProductType();
							utilMap(salesTarget, salesTargetKey);
						}
					}
				}
			}

			Map<String, String> targetComparision = new HashMap<String, String>();
			for (Map.Entry<String, Integer> entry : salesTarget.entrySet()) {
				if (entry.getKey().contains("SSP")) {
					if (entry.getValue() < dealerTarget.getDealerTargetSSP()) {
						targetComparision.put(dealerTarget.getSubProductTypeSSP(), "LT");
					} else {
						targetComparision.put(dealerTarget.getSubProductTypeSSP(), "GT");
					}
				} else if (entry.getKey().contains("OSP")) {
					if (entry.getValue() < dealerTarget.getDealerTargetOSP()) {
						targetComparision.put(dealerTarget.getSubProductTypeOSP(), "LT");
					} else {
						targetComparision.put(dealerTarget.getSubProductTypeOSP(), "GT");
					}
				}
			}

			Map<String, Integer> incentiveMap = new HashMap<String, Integer>();
			String incentiveKey = null;
			for (Map.Entry<String, String> entry : targetComparision.entrySet()) {
				for (IncentiveStructure inc : incStructureList) {
					if (entry.getKey().equals(inc.getSubProductType())
							&& entry.getValue().equals(inc.getPerformanceTarget())) {
						incentiveKey = inc.getProductType() + inc.getSubProductType() + inc.getNoOfServices();
						incentiveMap.put(incentiveKey, inc.getIncentives());
					}
				}
			}

			Integer totalIncentiveSSP = 0;
			Integer totalIncentiveOSP = 0;
			for (Map.Entry<String, Integer> entry : noOfClaimsAllowedCount.entrySet()) {
				for (Map.Entry<String, Integer> incentiveEntry : incentiveMap.entrySet()) {
					if (entry.getKey().equals(incentiveEntry.getKey()) && entry.getKey().contains("SSP")) {
						totalIncentiveSSP = totalIncentiveSSP + (entry.getValue() * incentiveEntry.getValue());
					} else if (entry.getKey().equals(incentiveEntry.getKey()) && entry.getKey().contains("OSP")) {
						totalIncentiveOSP = totalIncentiveOSP + (entry.getValue() * incentiveEntry.getValue());
					}
				}
			}

			Double targetAchievedPercentageSSP = 0.0;
			Double targetAchievedPercentageOSP = 0.0;
			for (Map.Entry<String, Integer> entry : salesTarget.entrySet()) {
				if (entry.getKey().contains("SSP") && (dealerTarget.getDealerTargetSSP() != 0)) {
					targetAchievedPercentageSSP = (double) Math
							.round((double) (entry.getValue() / (double) dealerTarget.getDealerTargetSSP()) * 100);
					incCalculationSSP.setTargetAchieved(entry.getValue().toString());
				} else if (entry.getKey().contains("OSP") && (dealerTarget.getDealerTargetOSP() != 0)) {
					targetAchievedPercentageOSP = (double) Math
							.round((double) (entry.getValue() / (double) dealerTarget.getDealerTargetOSP()) * 100);
					incCalculationOSP.setTargetAchieved(entry.getValue().toString());
				}
			}

			String incentiveCategorySSP = targetAchievedPercentageSSP >= 100.0 ? "100%" : "0%";
			String incentiveCategoryOSP = targetAchievedPercentageOSP >= 100.0 ? "100%" : "0%";

			incCalculationSSP.setDealerCode(dealerCode);
			incCalculationOSP.setDealerCode(dealerCode);
			incCalculationSSP.setDealerName(dealerTarget.getDealerName());
			incCalculationOSP.setDealerName(dealerTarget.getDealerName());
			incCalculationSSP.setProductType(dealerTarget.getProductType());
			incCalculationOSP.setProductType(dealerTarget.getProductType());
			incCalculationSSP.setSubProductType(dealerTarget.getSubProductTypeSSP());
			incCalculationOSP.setSubProductType(dealerTarget.getSubProductTypeOSP());
			incCalculationSSP.setTarget(dealerTarget.getDealerTargetSSP().toString());
			incCalculationOSP.setTarget(dealerTarget.getDealerTargetOSP().toString());
			incCalculationSSP.setAchievedPercentage(targetAchievedPercentageSSP.toString());
			incCalculationOSP.setAchievedPercentage(targetAchievedPercentageOSP.toString());
			incCalculationSSP.setIncentiveCategory(incentiveCategorySSP.toString());
			incCalculationOSP.setIncentiveCategory(incentiveCategoryOSP.toString());
			incCalculationSSP.setTotal(totalIncentiveSSP.toString());
			incCalculationOSP.setTotal(totalIncentiveOSP.toString());

			for (Map.Entry<String, Integer> entry : noOfClaimsAllowedCount.entrySet()) {
				if (entry.getKey().contains("SSP2")) {
					incCalculationSSP.setNoOfClaimsAllowed2(entry.getValue().toString());
				} else if (entry.getKey().contains("SSP3")) {
					incCalculationSSP.setNoOfClaimsAllowed3(entry.getValue().toString());
				} else if (entry.getKey().contains("SSP4")) {
					incCalculationSSP.setNoOfClaimsAllowed4(entry.getValue().toString());
				} else if (entry.getKey().contains("SSP7")) {
					incCalculationSSP.setNoOfClaimsAllowed7(entry.getValue().toString());
				} else if (entry.getKey().contains("OSP2")) {
					incCalculationOSP.setNoOfClaimsAllowed2(entry.getValue().toString());
				} else if (entry.getKey().contains("OSP3")) {
					incCalculationOSP.setNoOfClaimsAllowed3(entry.getValue().toString());
				}
			}

			incCalculationList.add(incentiveCalculationRepository.save(incCalculationSSP));
			incCalculationList.add(incentiveCalculationRepository.save(incCalculationOSP));
		});

		return incCalculationList;
	}

	@SuppressWarnings("unchecked")
	private static <T> Predicate<T> distinctByKeys(Function<? super T, ?>... keyExtractors) {
		final Map<List<?>, Boolean> seen = new ConcurrentHashMap<>();
		return t -> {
			final List<?> keys = Arrays.stream(keyExtractors).map(ke -> ke.apply(t)).collect(Collectors.toList());
			return seen.putIfAbsent(keys, Boolean.TRUE) == null;
		};
	}

	private void utilMap(Map<String, Integer> map, String key) {
		if (map.containsKey(key)) {
			map.put(key, map.get(key) + 1);
		} else {
			map.put(key, 1);
		}
	}
}
