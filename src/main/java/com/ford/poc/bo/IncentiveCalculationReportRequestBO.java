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
	
	String incentiveFromMonth;
	
	String incentiveFromYear;
	
	String incentiveToMonth;
	
	String incentiveToYear;
	
	
	
}
