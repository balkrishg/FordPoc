package com.ford.poc.bo;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IncentiveCalculationReportRequestBO {

	List<String> dealerCodes;
	
	String programCode;
	
	String incentiveFrom;//Q1 or OCT
	
	String incentiveFromYear;// 2018
	
	String incentiveTo;// Q3 or NOV
	
	String incentiveToYear;//2019
	
}
