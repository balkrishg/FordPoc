package com.ford.poc.bo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IncentiveProgramBO {		 
	  private String programCode;
	  private String programName;	  
	  private String dateFrom;
	  private String dateTo;
	  private String status;
	  private String statusMsg;
}
