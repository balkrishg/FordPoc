package com.ford.poc.service;

import org.junit.jupiter.api.Test;

public class IncentiveServiceImplTest {
	
	@Test
	public void testIncentiveServiceImpl() {
		IncentiveServiceImpl test=new IncentiveServiceImpl();
		System.out.println(test.getPeriodFromDateRange("OCT","2019","JAN","2020","Monthly"));
		System.out.println(test.getPeriodFromDateRange("Q1","2019","Q4","2020","Quarterly"));
		
	}

}
