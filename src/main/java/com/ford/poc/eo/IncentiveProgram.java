package com.ford.poc.eo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "INC_PGM_DATA")
@Getter
@Setter
@NoArgsConstructor
public class IncentiveProgram {
	
	  @Id
	  @Column(name = "PROGRAM_CODE", nullable = false, unique = true)
	  private String programCode;
	 
	  @Column(name = "PROGRAM_NAME", nullable = false)
	  private String programName;
	  
	  @Column(name = "SCHEDULE_SERVICE", nullable = false)
	  private String scheduleService;
	  
	  @Column(name = "STANDARD_SSP", nullable = false)
	  private String standardSSP;
	  
	  @Column(name = "FREE_SSP", nullable = false)
	  private String freeSSP;
	  
	  @Column(name = "FLEXI_SSP", nullable = false)
	  private String flexiSSP;
	  
	  @Column(name = "FLEXI_EW", nullable = false)
	  private String flexiEW;
}
