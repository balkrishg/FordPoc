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
	  private String scheduleService;
	  private String standardSSP;
	  private String freeSSP;
	  private String flexiSSP;
	  private String flexiEW;
	  private String status;
	  private String statusMsg;
}
