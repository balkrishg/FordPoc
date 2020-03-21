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
	
	@Column(name = "PRODUCT_TYPE")
	private String productType;

	@Column(name = "SUB_PRD_TYPE_OSP")
	private String subProductTypeOSP;

	@Column(name = "DEALER_TARGET_OSP")
	private String dealerTargetOSP;

	@Column(name = "SUB_PRD_TYPE_SSP")
	private String subProductTypeSSP;

	@Column(name = "DEALER_TARGET_SSP")
	private String dealerTargetSSP;

	@Column(name = "DEALER_TARGET_MONTH")
	private String dealerTargetMonth;
}