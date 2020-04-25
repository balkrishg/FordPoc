package com.ford.poc.service;

import org.junit.jupiter.api.Test;

public class IncentiveServiceImplTest {
	
	@Test
	public void testIncentiveServiceImpl() {
		IncentiveServiceImpl test=new IncentiveServiceImpl();
		System.out.println(test.getPeriodFromDateRange("OCT2019","JAN2020","Monthly"));
		System.out.println(test.getPeriodFromDateRange("JAN2019","DECEMBER2019","Quarterly"));
		System.out.println(test.getPeriodFromDateRange("JAN2019","DECEMBER2019","Bi-Monthly"));
		System.out.println(test.getPeriodFromDateRange("JAN2018","DECEMBER2019","Yearly"));
		
	}

}
