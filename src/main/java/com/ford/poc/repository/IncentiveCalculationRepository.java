package com.ford.poc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ford.poc.eo.IncentiveCalculation;

@Repository
public interface IncentiveCalculationRepository extends JpaRepository<IncentiveCalculation, Long> {
	List<IncentiveCalculation> findByDealerCodeAndDealerTargetMonth(String dealerCode, String dealerTargetMonth);
	
	@Query(value = "SELECT * FROM INC_DLR_TOTAL_INCENTIVE i WHERE i.DEALER_CODE IN (:dealerCodes) AND i.PROGRAM_CODE IN (:programCodes) and i.DEALER_TARGET_MONTH IN (:dealerTargetMonth)", nativeQuery = true)
	List<IncentiveCalculation> findByDealerCodesAndProgramCodesAndDealerTargetMonth(@Param("dealerCodes") List<String> dealerCodes, @Param("programCodes") List<String> programCodes,@Param("dealerTargetMonth") String dealerTargetMonth);
}
