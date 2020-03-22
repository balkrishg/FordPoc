package com.ford.poc.bo;

import java.util.List;

import com.ford.poc.eo.IncentiveContractSales;
import com.ford.poc.eo.IncentiveContractSalesCancellation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IncentiveContractSalesAndCancellationListBO {

	List<IncentiveContractSales> IncentiveContractSalesList;

	List<IncentiveContractSalesCancellation> IncentiveContractSalesCancellationList;

}
