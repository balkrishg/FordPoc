package com.ford.poc.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ford.poc.eo.IncentiveContractSales;

@Repository
public interface IncentiveContractSalesRepository extends JpaRepository<IncentiveContractSales, Long> {

	@Query(value = "SELECT * FROM INC_DLR_CONTRACT_SALES i WHERE i.CONTRACT_STATUS IN ('A','I','L','P','C') AND i.CONTRACT_REGISTRATION_DATE BETWEEN :effectiveDate AND :expiryDate AND i.DEALER_CODE =:dealerCode", nativeQuery = true)
	List<IncentiveContractSales> findByDealerCode(@Param("effectiveDate") Date effectiveDate,
			@Param("expiryDate") Date expiryDate, @Param("dealerCode") String dealerCode);

}
