package com.ford.poc.bo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IncentiveStructureBO {
	  private String incStructureId;
	  private String programCode;	  
	  private String programName;
	  private String dateFrom;
	  private String dateTo;
	  private String productType;
	  private String subProductType;
	  private String productSaleType;
	  private String serviceType;
	  private String recipient;
	  private String noOfServices;
	  private String performanceTarget;
	  private String incentives;
	  private String status;
	  private String statusMsg;
}