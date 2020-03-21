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
@Table(name = "INC_DLR_CONTRACT_SALES_CANCELLATION")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncentiveContractSalesCancellation {
	
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

	@Column(name = "SUB_PRD_TYPE")
	private String subProductType;

	@Column(name = "NO_OF_CLAIMS_ALLOWED")
	private Integer noOfClaimsAllowed;

	@Column(name = "PRODUCT_SALE_TYPE")
	private String productSaleType;

	@Column(name = "CONTRACT_TYPE")
	private String contractType;

	@Column(name = "CONTRACT_STATUS")
	private String contractStatus;

	@Column(name = "PRODUCT_CODE")
	private String productCode;

	@Column(name = "CONTRACT_PLAN_ODOMETER")
	private Long contractPlanOdometer;

	@Column(name = "CONTRACT_REGISTRATION_DATE")
	private Date contractRegistrationDate;

	@Column(name = "CONTRACT_CANCELLATION_DATE")
	private Date contractCancellationDate;

	@Column(name = "CONTRACT_PLAN_TERM")
	private Integer contractPlanTerm;
}
