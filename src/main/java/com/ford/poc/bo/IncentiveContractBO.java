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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contractRegistrationDate == null) ? 0 : contractRegistrationDate.hashCode());
		result = prime * result + ((contractStatus == null) ? 0 : contractStatus.hashCode());
		result = prime * result + ((contractType == null) ? 0 : contractType.hashCode());
		result = prime * result + ((dealerCode == null) ? 0 : dealerCode.hashCode());
		result = prime * result + ((noOfClaimsAllowed == null) ? 0 : noOfClaimsAllowed.hashCode());
		result = prime * result + ((productSaleType == null) ? 0 : productSaleType.hashCode());
		result = prime * result + ((productType == null) ? 0 : productType.hashCode());
		result = prime * result + ((subProductType == null) ? 0 : subProductType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IncentiveContractBO other = (IncentiveContractBO) obj;
		if (contractRegistrationDate == null) {
			if (other.contractRegistrationDate != null)
				return false;
		} else if (!contractRegistrationDate.equals(other.contractRegistrationDate))
			return false;
		if (contractStatus == null) {
			if (other.contractStatus != null)
				return false;
		} else if (!contractStatus.equals(other.contractStatus))
			return false;
		if (contractType == null) {
			if (other.contractType != null)
				return false;
		} else if (!contractType.equals(other.contractType))
			return false;
		if (dealerCode == null) {
			if (other.dealerCode != null)
				return false;
		} else if (!dealerCode.equals(other.dealerCode))
			return false;
		if (noOfClaimsAllowed == null) {
			if (other.noOfClaimsAllowed != null)
				return false;
		} else if (!noOfClaimsAllowed.equals(other.noOfClaimsAllowed))
			return false;
		if (productSaleType == null) {
			if (other.productSaleType != null)
				return false;
		} else if (!productSaleType.equals(other.productSaleType))
			return false;
		if (productType == null) {
			if (other.productType != null)
				return false;
		} else if (!productType.equals(other.productType))
			return false;
		if (subProductType == null) {
			if (other.subProductType != null)
				return false;
		} else if (!subProductType.equals(other.subProductType))
			return false;
		return true;
	}

}