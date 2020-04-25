package com.ford.poc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ford.poc.eo.IncentiveCalculation;

@Repository
public interface IncentiveCalculationRepository extends JpaRepository<IncentiveCalculation, Long> {
	List<IncentiveCalculation> findByDealerCodeAndDealerTargetPeriod(String dealerCode, String dealerTargetPeriod);
	List<IncentiveCalculation> findByDealerCodeAndProgramCode(String dealerCode, String programCode);
	
	@Query(value = "SELECT * FROM INC_DLR_TOTAL_INCENTIVE i WHERE i.DEALER_CODE IN (:dealerCodes) AND i.PROGRAM_CODE =:programCode and i.DEALER_TARGET_PERIOD IN (:dealerTargetPeriod)", nativeQuery = true)
	List<IncentiveCalculation> findByDealerCodesAndProgramCodesAndDealerTargetPeriod(@Param("dealerCodes") List<String> dealerCodes, @Param("programCode") String programCode,@Param("dealerTargetPeriod") String dealerTargetPeriod);
}
