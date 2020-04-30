package com.ford.poc.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
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

import com.ford.poc.bo.IncentiveCalculationReportBO;
import com.ford.poc.bo.IncentiveContractBO;
import com.ford.poc.eo.IncentiveCalculation;
import com.ford.poc.eo.IncentiveContractSales;
import com.ford.poc.eo.IncentiveContractSalesCancellation;
import com.ford.poc.eo.IncentiveDealerDetails;
import com.ford.poc.eo.IncentiveDealerTarget;
import com.ford.poc.eo.IncentiveProgram;
import com.ford.poc.eo.IncentiveStructure;
import com.ford.poc.helper.IncentiveConstants;
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
	public IncentiveDealerTarget getDealerTargetByMonth(String dealerCode, String dealerTargetMonthFrom, String dealerTargetMonthTo) {
		return incentiveDealerTargetRepository.findByDealerCodeAndDealerTargetMonthFromAndDealerTargetMonthTo(dealerCode, dealerTargetMonthFrom, dealerTargetMonthTo);
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
				.findByDealerCodeAndDealerTargetPeriod(dealerTarget.getDealerCode(),
						dealerTarget.getDealerTargetMonthFrom()+"-"+dealerTarget.getDealerTargetMonthTo());
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
				List<Date> listOfDays = getFromAndTodate(dealerTarget.getDealerTargetMonthFrom(), dealerTarget.getDealerTargetMonthTo());

				IncentiveCalculation incCalculationSSP = new IncentiveCalculation();
				IncentiveCalculation incCalculationOSP = new IncentiveCalculation();

				List<IncentiveContractSales> incContractSalesList = incentiveContractSalesRepository
						.findByDealerCode(listOfDays.get(0), listOfDays.get(1), dealerCode);
				//TODO: To confirm whether the cancellation records should also be selected as sales records from and to date. 
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
						if (entry.getKey().equals(incentiveEntry.getKey()) && entry.getKey().contains("SSP")) {
							totalIncentiveSSP = totalIncentiveSSP + (entry.getValue() * incentiveEntry.getValue());
							// Setting up values for AmountEarned values INC_DLR_TOTAL_INCENTIVE table
							if (entry.getKey().contains("SSP2")) {
								incCalculationSSP.setAmountEarnedCA2(
										entry.getValue() != null ? entry.getValue() * incentiveEntry.getValue() : 0);
							} else if (entry.getKey().contains("SSP3")) {
								incCalculationSSP.setAmountEarnedCA3(
										entry.getValue() != null ? entry.getValue() * incentiveEntry.getValue() : 0);
							} else if (entry.getKey().contains("SSP4")) {
								incCalculationSSP.setAmountEarnedCA4(
										entry.getValue() != null ? entry.getValue() * incentiveEntry.getValue() : 0);
							} else if (entry.getKey().contains("SSP7")) {
								incCalculationSSP.setAmountEarnedCA7(
										entry.getValue() != null ? entry.getValue() * incentiveEntry.getValue() : 0);
							}

						} else if (entry.getKey().equals(incentiveEntry.getKey()) && entry.getKey().contains("OSP")) {
							totalIncentiveOSP = totalIncentiveOSP + (entry.getValue() * incentiveEntry.getValue());

							if (entry.getKey().contains("OSP2")) {
								incCalculationOSP.setAmountEarnedCA2(
										entry.getValue() != null ? entry.getValue() * incentiveEntry.getValue() : 0);
							} else if (entry.getKey().contains("OSP3")) {
								incCalculationOSP.setAmountEarnedCA3(
										entry.getValue() != null ? entry.getValue() * incentiveEntry.getValue() : 0);
							}

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

						String incentiveCategorySSP = targetAchievedPercentageSSP >= 100 ? ">100%" : "<100%";

						incCalculationSSP.setDealerCode(dealerCode);
						incCalculationSSP.setDealerName(dealerTarget.getDealerName());
						incCalculationSSP.setProgramCode(incProgram.getProgramCode());
						incCalculationSSP.setSubProductType(dealerTarget.getSubProductTypeSSP());
						incCalculationSSP.setTarget(dealerTarget.getDealerTargetSSP());
						incCalculationSSP.setAchievedPercentage(targetAchievedPercentageSSP.intValue());
						incCalculationSSP.setIncentiveCategory(incentiveCategorySSP);
						incCalculationSSP.setTotal(totalIncentiveSSP);
						incCalculationSSP.setDealerTargetPeriod(getDealerTargetPeriod(dealerTarget.getDealerTargetMonthFrom(),dealerTarget.getDealerTargetMonthTo()));
						log.info("dealerTarget.getDealerTargetMonthFrom()+\"-\"+dealerTarget.getDealerTargetMonthTo()) : " + dealerTarget.getDealerTargetMonthFrom()+"-"+dealerTarget.getDealerTargetMonthTo());
						for (Map.Entry<String, Integer> entryClaimsCount : noOfClaimsAllowedCount.entrySet()) {
							if (entryClaimsCount.getKey().contains("SSP2")) {
								incCalculationSSP.setNoOfClaimsAllowed2(
										entryClaimsCount.getValue() != null ? entryClaimsCount.getValue() : 0);
							} else if (entryClaimsCount.getKey().contains("SSP3")) {
								incCalculationSSP.setNoOfClaimsAllowed3(
										entryClaimsCount.getValue() != null ? entryClaimsCount.getValue() : 0);
							} else if (entryClaimsCount.getKey().contains("SSP4")) {
								incCalculationSSP.setNoOfClaimsAllowed4(
										entryClaimsCount.getValue() != null ? entryClaimsCount.getValue() : 0);
							} else if (entryClaimsCount.getKey().contains("SSP7")) {
								incCalculationSSP.setNoOfClaimsAllowed7(
										entryClaimsCount.getValue() != null ? entryClaimsCount.getValue() : 0);
							}
						}

						incCalculationList.add(incCalculationSSP);
					} else if (entry.getKey().contains("OSP")) {

						if (entry.getKey().contains("OSP") && (dealerTarget.getDealerTargetOSP() != 0)) {
							targetAchievedPercentageOSP = (double) Math.round(
									(double) (entry.getValue() / (double) dealerTarget.getDealerTargetOSP()) * 100);
							incCalculationOSP.setTargetAchieved(entry.getValue());

							String incentiveCategoryOSP = targetAchievedPercentageOSP >= 100.0 ? ">100%" : "<100%";

							incCalculationOSP.setDealerCode(dealerCode);
							incCalculationOSP.setDealerName(dealerTarget.getDealerName());
							incCalculationOSP.setProgramCode(incProgram.getProgramCode());
							incCalculationOSP.setSubProductType(dealerTarget.getSubProductTypeOSP());
							incCalculationOSP.setTarget(dealerTarget.getDealerTargetOSP());
							incCalculationOSP.setAchievedPercentage(targetAchievedPercentageOSP.intValue());
							incCalculationOSP.setIncentiveCategory(incentiveCategoryOSP);
							incCalculationOSP.setTotal(totalIncentiveOSP);
							incCalculationOSP.setDealerTargetPeriod(getDealerTargetPeriod(dealerTarget.getDealerTargetMonthFrom(),dealerTarget.getDealerTargetMonthTo()));
							for (Map.Entry<String, Integer> entryClaimsCount : noOfClaimsAllowedCount.entrySet()) {
								if (entryClaimsCount.getKey().contains("OSP2")) {
									incCalculationOSP.setNoOfClaimsAllowed2(
											entryClaimsCount.getValue() != null ? entryClaimsCount.getValue() : 0);
								} else if (entryClaimsCount.getKey().contains("OSP3")) {
									incCalculationOSP.setNoOfClaimsAllowed3(
											entryClaimsCount.getValue() != null ? entryClaimsCount.getValue() : 0);
								}
							}
							incCalculationList.add(incCalculationOSP);
						}

					}
				}
			}
		});
		return incCalculationList;
	}

	private String getDealerTargetPeriod(String dealerTargetMonthFrom, String dealerTargetMonthTo) {		
		return (dealerTargetMonthTo==null? dealerTargetMonthFrom: (dealerTargetMonthFrom+"-"+dealerTargetMonthTo));
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
	public IncentiveCalculationReportBO getIncentiveCalculationList(List<String> dealerCodes,
			String programCode, String incentiveFrom, String incentiveFromYear, String incentiveTo, String incentiveToYear) throws Exception {
		List<String> errorMessages = new ArrayList<String>();
		
		IncentiveProgram incProgram= getIncentiveProgram(programCode);
		List<String> listOfMonths = getPeriodFromDateRange(incentiveFrom, incentiveFromYear, incentiveTo, incentiveToYear, incProgram.getPayoutFrequency());
		//List<String> listOfMonths = getPeriodFromDateRange(incentiveFromMonth, incentiveToMonth, incProgram.getPayoutFrequency());
		Map<String, List<IncentiveCalculation>> reportMap = new HashMap<String, List<IncentiveCalculation>>();
		List<IncentiveCalculation> incCalculationListSSP = new ArrayList<IncentiveCalculation>();
		List<IncentiveCalculation> incCalculationListOSP = new ArrayList<IncentiveCalculation>();
		List<IncentiveCalculation> incCalculationListTotal = new ArrayList<IncentiveCalculation>();

		for (String dealerTargetPeriod : listOfMonths) {
			List<IncentiveCalculation> incCalculationList = incentiveCalculationRepository
					.findByDealerCodesAndProgramCodesAndDealerTargetPeriod(dealerCodes, programCode, dealerTargetPeriod);
			log.info("incCalcList.size(): "+incCalculationList.size()+" dealerTargetPeriod: "+dealerTargetPeriod);
			
			List<String> errorDealerCodes=new ArrayList<String>();
			errorDealerCodes=dealerCodes;
			String totalIncMapKey = null;
			Map<String, IncentiveCalculation> totalIncMap = new HashMap<String, IncentiveCalculation>();
			for (IncentiveCalculation incCalculation : incCalculationList) {
				errorDealerCodes.remove(incCalculation.getDealerCode());
				if (incCalculation.getSubProductType().equals("SSP")) {
					incCalculationListSSP.add(incCalculation);
				} else if (incCalculation.getSubProductType().equals("OSP")) {
					incCalculationListOSP.add(incCalculation);
				}
				totalIncMapKey = incCalculation.getDealerCode() + incCalculation.getProgramCode()
						+ incCalculation.getDealerTargetPeriod();
				IncentiveCalculation incCal = new IncentiveCalculation();
				if (totalIncMap.containsKey(totalIncMapKey)) {
					IncentiveCalculation incCalBO = totalIncMap.get(totalIncMapKey);
					incCal.setDealerCode(incCalBO.getDealerCode());
					incCal.setDealerName(incCalBO.getDealerName());
					incCal.setProgramCode(incCalBO.getProgramCode());
					incCal.setSubProductType(incCalBO.getSubProductType());
					incCal.setNoOfClaimsAllowed2(
							incCalculation.getNoOfClaimsAllowed2() + incCalBO.getNoOfClaimsAllowed2());
					incCal.setNoOfClaimsAllowed3(
							incCalculation.getNoOfClaimsAllowed3() + incCalBO.getNoOfClaimsAllowed3());
					incCal.setNoOfClaimsAllowed4(
							incCalculation.getNoOfClaimsAllowed4() + incCalBO.getNoOfClaimsAllowed4());
					incCal.setNoOfClaimsAllowed7(
							incCalculation.getNoOfClaimsAllowed7() + incCalBO.getNoOfClaimsAllowed7());
					//incCal.setTargetAchieved(incCalculation.getTargetAchieved() + incCalBO.getTargetAchieved());
					//incCal.setTarget(incCalculation.getTarget() + incCalBO.getTarget());
					//incCal.setAchievedPercentage(
					//		incCalculation.getAchievedPercentage() + incCalBO.getAchievedPercentage());
					//incCal.setIncentiveCategory(
					//		incCalculation.getIncentiveCategory() + incCalBO.getIncentiveCategory());
					incCal.setAmountEarnedCA2(incCalculation.getAmountEarnedCA2() + incCalBO.getAmountEarnedCA2());
					incCal.setAmountEarnedCA3(incCalculation.getAmountEarnedCA3() + incCalBO.getAmountEarnedCA3());
					incCal.setAmountEarnedCA4(incCalculation.getAmountEarnedCA4() + incCalBO.getAmountEarnedCA4());
					incCal.setAmountEarnedCA7(incCalculation.getAmountEarnedCA7() + incCalBO.getAmountEarnedCA7());
					incCal.setTotal(incCalculation.getTotal() + incCalBO.getTotal());
					incCal.setDealerTargetPeriod(incCalculation.getDealerTargetPeriod());
					//log.info("incCalculation.getDealerTargetMonth() : " + incCalculation.getDealerTargetMonth());
					totalIncMap.put(totalIncMapKey, incCal);
				} else {
					incCal.setDealerCode(incCalculation.getDealerCode());
					incCal.setDealerName(incCalculation.getDealerName());
					incCal.setProgramCode(incCalculation.getProgramCode());
					incCal.setSubProductType("ALL");
					incCal.setNoOfClaimsAllowed2(incCalculation.getNoOfClaimsAllowed2());
					incCal.setNoOfClaimsAllowed3(incCalculation.getNoOfClaimsAllowed3());
					incCal.setNoOfClaimsAllowed4(incCalculation.getNoOfClaimsAllowed4());
					incCal.setNoOfClaimsAllowed7(incCalculation.getNoOfClaimsAllowed7());
					//incCal.setTargetAchieved(incCalculation.getTargetAchieved());
					//incCal.setTarget(incCalculation.getTarget());
					//incCal.setAchievedPercentage(incCalculation.getAchievedPercentage());
					//incCal.setIncentiveCategory(incCalculation.getIncentiveCategory());
					incCal.setAmountEarnedCA2(incCalculation.getAmountEarnedCA2());
					incCal.setAmountEarnedCA3(incCalculation.getAmountEarnedCA3());
					incCal.setAmountEarnedCA4(incCalculation.getAmountEarnedCA4());
					incCal.setAmountEarnedCA7(incCalculation.getAmountEarnedCA7());
					incCal.setTotal(incCalculation.getTotal());
					incCal.setDealerTargetPeriod(incCalculation.getDealerTargetPeriod());
					//log.info("incCalculation.getDealerTargetMonth() : " + incCalculation.getDealerTargetMonth());
					totalIncMap.put(totalIncMapKey, incCal);
				}
			}
			if(errorDealerCodes.size()!=0) {
				errorMessages.add("DealerTarget not available for the period "+ dealerTargetPeriod + "for the dealer codes"+errorDealerCodes);
			}
			for (Map.Entry<String, IncentiveCalculation> totalIncentive : totalIncMap.entrySet()) {
				incCalculationListTotal.add(totalIncentive.getValue());
			}

			reportMap.put("SSP", incCalculationListSSP);
			reportMap.put("OSP", incCalculationListOSP);
			reportMap.put("Total", incCalculationListTotal);
		}
		IncentiveCalculationReportBO result=new IncentiveCalculationReportBO();
		result.setReport(reportMap);
		result.setErrorMessages(errorMessages);
		return result;

	}

	public List<String> getPeriodFromDateRange(String incentiveFrom, String incentiveFromYear, String incentiveTo,
			String incentiveToYear, String payoutFrequency) {
		List<String> listOfPeriods = new ArrayList<String>();
		if(IncentiveConstants.MONTHLY.equals(payoutFrequency)) {
			if(incentiveTo==null || incentiveToYear==null) {
				listOfPeriods.add(incentiveFrom+incentiveFromYear);							
			}
			else {				
				DateFormat formater = new SimpleDateFormat("MMMyyyy");
				Calendar beginCalendar = Calendar.getInstance();
				Calendar finishCalendar = Calendar.getInstance();

				try {
					beginCalendar.setTime(formater.parse(incentiveFrom+incentiveFromYear));
					finishCalendar.setTime(formater.parse(incentiveTo+incentiveToYear));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				while (beginCalendar.before(finishCalendar) || beginCalendar.equals(finishCalendar)) {
					
					String date = formater.format(beginCalendar.getTime()).toUpperCase();
					listOfPeriods.add(date);
					log.info("period"+ date);					
					beginCalendar.add(Calendar.MONTH, 1);
					
				}
				
			}
		}
		else if(IncentiveConstants.QUARTERLY.equals(payoutFrequency)) {
			if(incentiveTo==null || incentiveToYear==null) {
				listOfPeriods.add(getPeriodFromQuarter(incentiveFrom,incentiveFromYear));
			}
			else {				
				boolean exit=false;
				String tempQuarter=incentiveFrom;
				String tempYear=incentiveFromYear;
				listOfPeriods.add(getPeriodFromQuarter(incentiveFrom,incentiveFromYear));
				while(!(tempQuarter.equals(incentiveTo) && tempYear.equals(incentiveToYear))) {					
					tempQuarter=getNextQuarter(tempQuarter);
					if(IncentiveConstants.Q1.equals(tempQuarter)) {
						tempYear=getNextYear(tempYear);
					}
					listOfPeriods.add(getPeriodFromQuarter(tempQuarter,tempYear));
					if(tempQuarter.equals(incentiveTo) && tempYear.equals(incentiveToYear)) {
						exit=true;
					}
				}
			}
			
		}
	return listOfPeriods;
	}
	
	private String getNextYear(String year) {
		Integer nextYear=Integer.parseInt(year)+1;
		return nextYear.toString();
	}

	private String getNextQuarter(String quarter) {
		String nextQuarter=null;
		if(IncentiveConstants.Q1.equals(quarter)) {
			nextQuarter=IncentiveConstants.Q2;
		}
		else if(IncentiveConstants.Q2.equals(quarter)) {
			nextQuarter=IncentiveConstants.Q3;
		}
		else if(IncentiveConstants.Q3.equals(quarter)) {
			nextQuarter=IncentiveConstants.Q4;
		}
		else if(IncentiveConstants.Q4.equals(quarter)) {
			nextQuarter=IncentiveConstants.Q1;
		}
		return nextQuarter;
	}

	private String getPeriodFromQuarter(String quarter, String year) {
		String period=null;
		if(IncentiveConstants.Q1.equals(quarter)) {
			period=IncentiveConstants.Q1_PERIOD.replace("<Year>", year);
		}
		else if(IncentiveConstants.Q2.equals(quarter)) {
			period=IncentiveConstants.Q2_PERIOD.replace("<Year>", year);
		}
		else if(IncentiveConstants.Q3.equals(quarter)) {
			period=IncentiveConstants.Q3_PERIOD.replace("<Year>", year);
		}
		else if(IncentiveConstants.Q4.equals(quarter)) {
			period=IncentiveConstants.Q4_PERIOD.replace("<Year>", year);
		}
		return period;
	}

	private List<Date> getFromAndTodate(String fromMonthYear, String toMonthYear) {
		Date date1 = null;
		Date date2 = null;
        List<Date> listOfdates = new ArrayList<Date>();
		DateFormat formater1 = new SimpleDateFormat("MMMyyyy");
		Calendar beginCalendar = Calendar.getInstance();
		Calendar endCalendar = Calendar.getInstance();
		try {
			beginCalendar.setTime(formater1.parse(fromMonthYear));
			if(toMonthYear==null)
				endCalendar.setTime(formater1.parse(fromMonthYear));
			else
				endCalendar.setTime(formater1.parse(toMonthYear));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// Set the day of the month to the first day of the month
		beginCalendar.set(Calendar.DAY_OF_MONTH, 
				beginCalendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		date1 = beginCalendar.getTime();
		listOfdates.add(date1);
		log.info("Month start Date : "+date1);
		endCalendar.set(Calendar.DAY_OF_MONTH,beginCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		date2 = endCalendar.getTime();
		log.info("Month end Date : "+date2);
		listOfdates.add(date2);
		return listOfdates;
	}

}