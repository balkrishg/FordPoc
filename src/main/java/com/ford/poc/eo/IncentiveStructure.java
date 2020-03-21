package com.ford.poc.eo;

import java.util.Date;

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
@Table(name = "INC_PRD_STRUCTURE")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncentiveStructure {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "INC_STRUCTURE_ID", nullable = false, unique = true)
	private Long incStructureId;

	@Column(name = "PROGRAM_CODE", nullable = false)
	private String programCode;

	@Column(name = "PROGRAM_NAME", nullable = false)
	private String programName;

	@Column(name = "DATE_FROM", nullable = false)
	private Date dateFrom;

	@Column(name = "DATE_TO", nullable = false)
	private Date dateTo;

	@Column(name = "PRODUCT_TYPE", nullable = false)
	private String productType;

	@Column(name = "SUB_PRODUCT_TYPE", nullable = false)
	private String subProductType;

	@Column(name = "PRODUCT_SALE_TYPE", nullable = false)
	private String productSaleType;

	@Column(name = "SERVICE_TYPE", nullable = false)
	private String serviceType;

	@Column(name = "RECIPIENT", nullable = false)
	private String recipient;

	@Column(name = "NO_OF_SERVICES", nullable = false)
	private Integer noOfServices;

	@Column(name = "PERFORMANCE_TARGET", nullable = false)
	private String performanceTarget;

	@Column(name = "INCENTIVES", nullable = false)
	private Integer incentives;
}