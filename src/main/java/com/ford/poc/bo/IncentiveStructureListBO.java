package com.ford.poc.bo;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IncentiveStructureListBO {

	private String programCode;
	private String programName;
	private String dateFrom;
	private String dateTo;

	List<IncentiveStructureBO> incentiveStructureBOList;

}
