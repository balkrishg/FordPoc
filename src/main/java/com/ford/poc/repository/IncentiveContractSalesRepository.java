package com.ford.poc.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ford.poc.eo.IncentiveContractSales;

@Repository
public interface IncentiveContractSalesRepository extends JpaRepository<IncentiveContractSales, Long>{

	@Query(value="select * from INC_DLR_CONTRACT_SALES i where i.CONTRACT_STATUS in ('A','I','L','P','C') and i.CONTRACT_REGISTRATION_DATE BETWEEN :effectiveDate AND :expiryDate", 
			nativeQuery=true)
	List<IncentiveContractSales> findByEffectiveDateAndExpiryDate(@Param("effectiveDate") Date effectiveDate,@Param("expiryDate") Date expiryDate);

}
