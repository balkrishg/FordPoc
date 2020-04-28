package com.ford.poc.bo;

import java.util.List;
import java.util.Map;

import com.ford.poc.eo.IncentiveCalculation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IncentiveCalculationReportBO {

	private Map<String, List<IncentiveCalculation>> report;
	private List<String> errorMessages;
}
