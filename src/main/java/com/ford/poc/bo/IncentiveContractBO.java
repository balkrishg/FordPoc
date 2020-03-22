package com.ford.poc.bo;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IncentiveContractBO {
	private String dealerCode;
	private String productType;
	private String subProductType;
	private Integer noOfClaimsAllowed;
	private String productSaleType;
	private String contractType;
	private String contractStatus;
	private Date contractRegistrationDate;
}