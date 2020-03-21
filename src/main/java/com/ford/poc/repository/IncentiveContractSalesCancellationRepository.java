package com.ford.poc.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ford.poc.eo.IncentiveContractSalesCancellation;

@Repository
public interface IncentiveContractSalesCancellationRepository extends JpaRepository<IncentiveContractSalesCancellation, Long>{
	
	@Query(value="select * from INC_DLR_CONTRACT_SALES_CANCELLATION i where i.CONTRACT_STATUS in ('A','I','L','P','C') and i.CONTRACT_CANCELLATION_DATE BETWEEN :effectiveDate AND :expiryDate", 
			nativeQuery=true)
	List<IncentiveContractSalesCancellation> findByEffectiveDateAndExpiryDate(@Param("effectiveDate") Date effectiveDate,@Param("expiryDate") Date expiryDate);

}
