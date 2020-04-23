package com.ford.poc.eo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "INC_DLR_INCENTIVE_DETAIL")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncentiveCalcDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "INC_CALC_DETAIL_ID", nullable = false, unique = true)
	private Long incCalcDetailId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ID", nullable=false)
    private IncentiveCalculation incCalc;
	
	@Column(name = "NO_OF_SERVICES")
	private int noOfServices;
	
	@Column(name="ACTUAL_SALES")
	private int actualSales;
	
	@Column(name="INCENTIVE_CALCULATED")
	private int incentiveCalculated;

}
