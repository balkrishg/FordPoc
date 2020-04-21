package com.ford.poc.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ford.poc.eo.IncentiveDealerTarget;

@Repository
public interface IncentiveDealerTargetRepository extends JpaRepository<IncentiveDealerTarget, Long>{

	IncentiveDealerTarget findByDealerCode(String dealerCode);
	
	//List<IncentiveDealerTarget> findByDealerCodeAndDealerTarget(String dealerCode);
	
	IncentiveDealerTarget findByDealerCodeAndDealerTargetMonth(String dealerCode, String dealerTargetMonth);
}
