package com.ford.poc.eo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "INC_DLR_PRD_TARGET")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncentiveDealerTarget {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, unique = true)
	private Long id;

	@Column(name = "DEALER_CODE")
	private String dealerCode;

	@Column(name = "DEALER_NAME")
	private String dealerName;
	
	@Column(name = "PROGRAM_CODE", nullable = false)
	private String programCode;
	
	@Column(name = "SUB_PRD_TYPE_OSP")
	private String subProductTypeOSP;

	@Column(name = "DEALER_TARGET_OSP")
	private Integer dealerTargetOSP;

	@Column(name = "SUB_PRD_TYPE_SSP")
	private String subProductTypeSSP;

	@Column(name = "DEALER_TARGET_SSP")
	private Integer dealerTargetSSP;

	@Column(name = "DEALER_TARGET_PERIOD")
	private String dealerTargetPeriod;
	
	
}