package com.ford.poc.eo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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

	@Column(name = "PROGRAM_CODE")
	private String programCode;

	@Column(name = "SUB_PRD_TYPE")
	private String subProductType;
	
	
	@OneToMany(
	        mappedBy = "incCalc",
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
	private List<IncentiveCalcDetail> incCalcDetail = new ArrayList<>();
	
	@Column(name = "TARGET_ACHIEVED")
	private int targetAchieved;

	@Column(name = "TARGET")
	private int target;

	@Column(name = "ACHIEVED_PERCENTAGE")
	private int achievedPercentage;

	@Column(name = "INCENTIVE_CATEGORY")
	private int incentiveCategory;
	
	@Column(name = "TOTAL")
	private int total;
	
	@Column(name = "DEALER_TARGET_MONTH")
	private String dealerTargetMonth;

}
