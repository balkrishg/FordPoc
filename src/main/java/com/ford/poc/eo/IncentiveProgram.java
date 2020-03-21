package com.ford.poc.eo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "INC_PGM_DATA")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncentiveProgram {
	
	  @Id
	  @Column(name = "PROGRAM_CODE", nullable = false, unique = true)
	  private String programCode;
	 
	  @Column(name = "PROGRAM_NAME", nullable = false)
	  private String programName;
	  
	  @Column(name = "DATE_FROM", nullable = false)
	  private Date dateFrom;
	  
	  @Column(name = "DATE_TO", nullable = false)
	  private Date dateTo;

}
