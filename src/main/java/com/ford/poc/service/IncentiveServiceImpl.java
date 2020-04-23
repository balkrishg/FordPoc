package com.ford.poc.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ford.poc.bo.IncentiveContractBO;
import com.ford.poc.eo.IncentiveCalcDetail;
import com.ford.poc.eo.IncentiveCalculation;
import com.ford.poc.eo.IncentiveContractSales;
import com.ford.poc.eo.IncentiveContractSalesCancellation;
import com.ford.poc.eo.IncentiveDealerDetails;
import com.ford.poc.eo.IncentiveDealerTarget;
import com.ford.poc.eo.IncentiveProgram;
import com.ford.poc.eo.IncentiveStructure;
import com.ford.poc.helper.IncentiveHelper;
import com.ford.poc.repository.IncentiveCalculationRepository;
import com.ford.poc.repository.IncentiveContractSalesCancellationRepository;
import com.ford.poc.repository.IncentiveContractSalesRepository;
import com.ford.poc.repository.IncentiveDealerDetailsRepository;
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
	private IncentiveDealerDetailsRepository incentiveDealerDetailsRepository;

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

	Log log = LogFactory.getLog("IncentiveServiceImpl");
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
	public List<IncentiveDealerDetails> getAllDealerCodes() {
		return incentiveDealerDetailsRepository.findAll();
	}

	@Override
	public IncentiveDealerTarget getDealerTarget(String dealerCode) {
		return incentiveDealerTargetRepository.findByDealerCode(dealerCode);
	}

	@Override
	public IncentiveDealerTarget getDealerTargetByMonth(String dealerCode, String dealerTargetMonth) {
		return incentiveDealerTargetRepository.findByDealerCodeAndDealerTargetMonth(dealerCode, dealerTargetMonth);
	}

	@Override
	public List<IncentiveDealerTarget> getDealerTargetByDealerCodeAndProgramCode(String dealerCode,
			String programCode) {
		// return
		// incentiveDealerTargetRepository.findByDealerCodeAndDealerTarget(dealerCode);

		return incentiveDealerTargetRepository.findByDealerCodeAndProgramCode(dealerCode, programCode);
	}

	@Override
	public void saveIncentiveCalculationList(List<IncentiveCalculation> incCalculationList,
			IncentiveDealerTarget dealerTarget) {
		List<IncentiveCalculation> incCalculationEOList = incentiveCalculationRepository
				.findByDealerCodeAndDealerTargetMonth(dealerTarget.getDealerCode(),
						dealerTarget.getDealerTargetMonth());
		if (incCalculationEOList != null) {
			incentiveCalculationRepository.deleteAll(incCalculationEOList);
		}
		incentiveCalculationRepository.saveAll(incCalculationList);
	}

	@Override
	public void saveIncentiveCalculationList(List<IncentiveCalculation> incCalculationList) {
		String dealerCode = null;
		String programCode = null;
		for (IncentiveCalculation incentiveCalculation : incCalculationList) {
			dealerCode = incentiveCalculation.getDealerCode();
			programCode = incentiveCalculation.getProgramCode();
			break;
		}
		List<IncentiveCalculation> incCalculationEOList = incentiveCalculationRepository
				.findByDealerCodeAndProgramCode(dealerCode, programCode);
		if (incCalculationEOList != null) {
			incentiveCalculationRepository.deleteAll(incCalculationEOList);
		}
		incentiveCalculationRepository.saveAll(incCalculationList);
	}

	// Note: This method is used only for POC purpose.
	// To calculate the incentive for dealer based on static data only.
	// Logic as to be completely changed for dynamic values.
	@SuppressWarnings("unchecked")
	@Override
	public List<IncentiveCalculation> calculateIncentiveForParticularDealer(String dealerCode) {

		List<IncentiveCalculation> incCalculationList = new ArrayList<IncentiveCalculation>();
		getAllIncentiveProgram().stream().forEach(incProgram -> {
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

			List<IncentiveDealerTarget> listDealerTarget = getDealerTargetByDealerCodeAndProgramCode(dealerCode,
					incProgram.getProgramCode());
			for (IncentiveDealerTarget dealerTarget : listDealerTarget) {

				IncentiveCalculation incCalculationSSP = new IncentiveCalculation();
				List<IncentiveCalcDetail> incCalcDetailListSSP=new ArrayList<IncentiveCalcDetail>();
				IncentiveCalculation incCalculationOSP = new IncentiveCalculation();
				List<IncentiveCalcDetail> incCalcDetailListOSP=new ArrayList<IncentiveCalcDetail>();
				
				List<IncentiveContractSales> incContractSalesList = incentiveContractSalesRepository
						.findByDealerCode(incProgram.getDateFrom(), incProgram.getDateTo(), dealerCode);
				List<IncentiveContractSalesCancellation> incContractSalesCancellationList = incentiveContractSalesCancellationRepository
						.findByDealerCode(incProgram.getDateFrom(), incProgram.getDateTo(), dealerCode);

				List<IncentiveContractBO> incContractSalesBOList = new ArrayList<IncentiveContractBO>();
				List<IncentiveContractBO> incContractSalesCancellationBOList = new ArrayList<IncentiveContractBO>();
				incContractSalesBOList
						.addAll(incentiveHelper.convertIncentiveContractSalesEoToBo(incContractSalesList));
				incContractSalesCancellationBOList.addAll(incentiveHelper
						.convertIncentiveContractSalesCancellationEoToBo(incContractSalesCancellationList));

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
										|| p1.getContractType().equalsIgnoreCase("Sales")) {
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
						// log.info("entry.getKey() " +entry.getKey() +" Value " +entry.getValue());
						// log.info("incentiveEntry.getKey() "+incentiveEntry.getKey()+" Value
						// "+incentiveEntry.getValue());
						IncentiveCalcDetail incCalcDetail=new IncentiveCalcDetail();
						if (entry.getKey().equals(incentiveEntry.getKey()) && entry.getKey().contains("SSP")) {
							totalIncentiveSSP = totalIncentiveSSP + (entry.getValue() * incentiveEntry.getValue());
							// Setting up values for AmountEarned values INC_DLR_TOTAL_INCENTIVE table	
							incCalcDetail.setIncCalc(incCalculationSSP);
							incCalcDetail.setActualSales(entry.getValue() != null ? entry.getValue() : 0);
							incCalcDetail.setIncentiveCalculated(entry.getValue() != null ? entry.getValue() * incentiveEntry.getValue() : 0);
							if (entry.getKey().contains("SSP2")) {						
								incCalcDetail.setNoOfServices(2);																
							} else if (entry.getKey().contains("SSP3")) {
								incCalcDetail.setNoOfServices(3);
							} else if (entry.getKey().contains("SSP4")) {
								incCalcDetail.setNoOfServices(4);
							} else if (entry.getKey().contains("SSP7")) {
								incCalcDetail.setNoOfServices(7);
							}
							incCalcDetailListSSP.add(incCalcDetail);
							incCalculationSSP.setIncCalcDetail(incCalcDetailListSSP);

						} else if (entry.getKey().equals(incentiveEntry.getKey()) && entry.getKey().contains("OSP")) {
							totalIncentiveOSP = totalIncentiveOSP + (entry.getValue() * incentiveEntry.getValue());
							incCalcDetail.setIncCalc(incCalculationOSP);
							incCalcDetail.setActualSales(entry.getValue() != null ? entry.getValue() : 0);
							incCalcDetail.setIncentiveCalculated(entry.getValue() != null ? entry.getValue() * incentiveEntry.getValue() : 0);
							if (entry.getKey().contains("OSP2")) {
								incCalcDetail.setNoOfServices(2);
							} else if (entry.getKey().contains("OSP3")) {
								incCalcDetail.setNoOfServices(3);
							}
							incCalcDetailListOSP.add(incCalcDetail);
							incCalculationOSP.setIncCalcDetail(incCalcDetailListOSP);
						}
					}
				}

				Double targetAchievedPercentageSSP = 0.0;
				Double targetAchievedPercentageOSP = 0.0;

				for (Map.Entry<String, Integer> entry : salesTarget.entrySet()) {
					if (entry.getKey().contains("SSP")) {

						if (entry.getKey().contains("SSP") && (dealerTarget.getDealerTargetSSP() != 0)) {
							targetAchievedPercentageSSP = (double) Math.round(
									(double) (entry.getValue() / (double) dealerTarget.getDealerTargetSSP()) * 100);
							incCalculationSSP.setTargetAchieved(entry.getValue());
						}

						Integer incentiveCategorySSP = targetAchievedPercentageSSP >= 100 ? 100 : 0;

						incCalculationSSP.setDealerCode(dealerCode);
						incCalculationSSP.setDealerName(dealerTarget.getDealerName());
						incCalculationSSP.setProgramCode(incProgram.getProgramCode());
						incCalculationSSP.setSubProductType(dealerTarget.getSubProductTypeSSP());
						incCalculationSSP.setTarget(dealerTarget.getDealerTargetSSP());
						incCalculationSSP.setAchievedPercentage(targetAchievedPercentageSSP.intValue());
						incCalculationSSP.setIncentiveCategory(incentiveCategorySSP);
						incCalculationSSP.setTotal(totalIncentiveSSP);
						incCalculationSSP.setDealerTargetMonth(dealerTarget.getDealerTargetMonth());
						log.info("dealerTarget.getDealerTargetMonth() : " + dealerTarget.getDealerTargetMonth());
						incCalculationList.add(incCalculationSSP);
					} else if (entry.getKey().contains("OSP")) {

						if (entry.getKey().contains("OSP") && (dealerTarget.getDealerTargetOSP() != 0)) {
							targetAchievedPercentageOSP = (double) Math.round(
									(double) (entry.getValue() / (double) dealerTarget.getDealerTargetOSP()) * 100);
							incCalculationOSP.setTargetAchieved(entry.getValue());

							Integer incentiveCategoryOSP = targetAchievedPercentageOSP >= 100.0 ? 100 : 0;

							incCalculationOSP.setDealerCode(dealerCode);
							incCalculationOSP.setDealerName(dealerTarget.getDealerName());
							incCalculationOSP.setProgramCode(incProgram.getProgramCode());
							incCalculationOSP.setSubProductType(dealerTarget.getSubProductTypeOSP());
							incCalculationOSP.setTarget(dealerTarget.getDealerTargetOSP());
							incCalculationOSP.setAchievedPercentage(targetAchievedPercentageOSP.intValue());
							incCalculationOSP.setIncentiveCategory(incentiveCategoryOSP);
							incCalculationOSP.setTotal(totalIncentiveOSP);
							incCalculationOSP.setDealerTargetMonth(dealerTarget.getDealerTargetMonth());
							incCalculationList.add(incCalculationOSP);
						}

					}
				}
			}
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

	// Note: This method is used only for POC purpose.
	// To get the incentive calculation report for dealer based on static data only.
	// Logic as to be completely changed for dynamic values.
	public Map<String, List<IncentiveCalculation>> getIncentiveCalculationList(List<String> dealerCodes,
			List<String> programCodes, String IncentiveFrom, String IncentiveTo) {
		List<String> listOfMonths = getMonthsFromDateRange(IncentiveFrom, IncentiveTo);
		Map<String, List<IncentiveCalculation>> reportMap = new HashMap<String, List<IncentiveCalculation>>();
		List<IncentiveCalculation> incCalculationListSSP = new ArrayList<IncentiveCalculation>();
		List<IncentiveCalculation> incCalculationListOSP = new ArrayList<IncentiveCalculation>();
		List<IncentiveCalculation> incCalculationListTotal = new ArrayList<IncentiveCalculation>();

		for (String dealerTargetMonth : listOfMonths) {
			List<IncentiveCalculation> incCalculationList = incentiveCalculationRepository
					.findByDealerCodesAndProgramCodesAndDealerTargetMonth(dealerCodes, programCodes, dealerTargetMonth);

			String totalIncMapKey = null;
			Map<String, IncentiveCalculation> totalIncMap = new HashMap<String, IncentiveCalculation>();
			for (IncentiveCalculation incCalculation : incCalculationList) {
				if (incCalculation.getSubProductType().equals("SSP")) {
					incCalculationListSSP.add(incCalculation);
				} else if (incCalculation.getSubProductType().equals("OSP")) {
					incCalculationListOSP.add(incCalculation);
				}
				totalIncMapKey = incCalculation.getDealerCode() + incCalculation.getProgramCode()
						+ incCalculation.getDealerTargetMonth();
				IncentiveCalculation incCal = new IncentiveCalculation();
				if (totalIncMap.containsKey(totalIncMapKey)) {
					IncentiveCalculation incCalBO = totalIncMap.get(totalIncMapKey);
					incCal.setDealerCode(incCalBO.getDealerCode());
					incCal.setDealerName(incCalBO.getDealerName());
					incCal.setProgramCode(incCalBO.getProgramCode());
					incCal.setSubProductType(incCalBO.getSubProductType());
					for(IncentiveCalcDetail incCalDetailBO: incCalBO.getIncCalcDetail()) {
						for(IncentiveCalcDetail incentiveCalculationDetail: incCalculation.getIncCalcDetail()) {
							if(incCalDetailBO.getNoOfServices()==incentiveCalculationDetail.getNoOfServices()) {
								int actualSales=incentiveCalculationDetail.getActualSales()+incCalDetailBO.getActualSales();
								int incCalculated=incentiveCalculationDetail.getIncentiveCalculated()+incCalDetailBO.getIncentiveCalculated();
								incCalDetailBO.setActualSales(actualSales);
								incCalDetailBO.setIncentiveCalculated(incCalculated);
							}
						}
					}
					
					incCal.setTargetAchieved(incCalculation.getTargetAchieved() + incCalBO.getTargetAchieved());
					incCal.setTarget(incCalculation.getTarget() + incCalBO.getTarget());
					incCal.setAchievedPercentage(
							incCalculation.getAchievedPercentage() + incCalBO.getAchievedPercentage());
					incCal.setIncentiveCategory(
							incCalculation.getIncentiveCategory() + incCalBO.getIncentiveCategory());
					incCal.setTotal(incCalculation.getTotal() + incCalBO.getTotal());
					incCal.setDealerTargetMonth(incCalculation.getDealerTargetMonth());
					//log.info("incCalculation.getDealerTargetMonth() : " + incCalculation.getDealerTargetMonth());
					totalIncMap.put(totalIncMapKey, incCal);
				} else {
					incCal.setDealerCode(incCalculation.getDealerCode());
					incCal.setDealerName(incCalculation.getDealerName());
					incCal.setProgramCode(incCalculation.getProgramCode());
					incCal.setSubProductType("ALL");					
					incCal.setTargetAchieved(incCalculation.getTargetAchieved());
					incCal.setTarget(incCalculation.getTarget());
					incCal.setAchievedPercentage(incCalculation.getAchievedPercentage());
					incCal.setIncentiveCategory(incCalculation.getIncentiveCategory());
					incCal.setIncCalcDetail(incCalculation.getIncCalcDetail());
					incCal.setTotal(incCalculation.getTotal());
					incCal.setDealerTargetMonth(incCalculation.getDealerTargetMonth());
					//log.info("incCalculation.getDealerTargetMonth() : " + incCalculation.getDealerTargetMonth());
					totalIncMap.put(totalIncMapKey, incCal);
				}
			}

			for (Map.Entry<String, IncentiveCalculation> totalIncentive : totalIncMap.entrySet()) {
				incCalculationListTotal.add(totalIncentive.getValue());
			}

			reportMap.put("SSP", incCalculationListSSP);
			reportMap.put("OSP", incCalculationListOSP);
			reportMap.put("Total", incCalculationListTotal);
		}
		return reportMap;

	}

	private List<String> getMonthsFromDateRange(String IncentiveFrom, String IncentiveTo) {
		DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
		List<String> listOfMonths = new ArrayList<String>();
		Calendar beginCalendar = Calendar.getInstance();
		Calendar finishCalendar = Calendar.getInstance();

		try {
			beginCalendar.setTime(formater.parse(IncentiveFrom));
			finishCalendar.setTime(formater.parse(IncentiveTo));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		DateFormat formater1 = new SimpleDateFormat("MMMYY");
		while (beginCalendar.before(finishCalendar)) {
			// add one month to date per loop
			String date = formater1.format(beginCalendar.getTime()).toUpperCase();
			listOfMonths.add(date);
			System.out.println(date);
			beginCalendar.add(Calendar.MONTH, 1);
		}
		return listOfMonths;
	}

}