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
@Table(name = "INC_DLR_TOTAL_INCENTIVE")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncentiveCalculation {
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

	@Column(name = "NO_OF_CLAIMS_ALLOWED2")
	private String noOfClaimsAllowed2;

	@Column(name = "NO_OF_CLAIMS_ALLOWED3")
	private String noOfClaimsAllowed3;

	@Column(name = "NO_OF_CLAIMS_ALLOWED4")
	private String noOfClaimsAllowed4;

	@Column(name = "NO_OF_CLAIMS_ALLOWED7")
	private String noOfClaimsAllowed7;

	@Column(name = "TARGET_ACHIEVED")
	private String targetAchieved;

	@Column(name = "TARGET")
	private String target;

	@Column(name = "ACHIEVED_PERCENTAGE")
	private String achievedPercentage;

	@Column(name = "INCENTIVE_CATEGORY")
	private String incentiveCategory;

	@Column(name = "TOTAL")
	private String total;

}
