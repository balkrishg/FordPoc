package com.ford.poc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ford.poc.eo.IncentiveStructure;

@Repository
public interface IncentiveStructureRepository extends JpaRepository<IncentiveStructure, Long> {

	List<IncentiveStructure> findByProgramCode(String programCode);

	List<IncentiveStructure> findByProgramCodeAndProductType(String programCode, String productType);

	@Query(value = "SELECT * FROM INC_PRD_STRUCTURE i WHERE i.PROGRAM_CODE =:programCode AND i.PRODUCT_TYPE =:productType AND i.SUB_PRODUCT_TYPE =:subProductType AND i.PRODUCT_SALE_TYPE =:productSaleType AND i.CONTRACT_TYPE IN ('ALL', :contractType) AND i.RECIPIENT =:recipient AND i.NO_OF_SERVICES =:noOfServices AND i.PERFORMANCE_TARGET =:performanceTarget", nativeQuery = true)
	IncentiveStructure findDuplicates(@Param("programCode") String programCode,
			@Param("productType") String productType, @Param("subProductType") String subProductType,
			@Param("productSaleType") String productSaleType, @Param("contractType") String contractType,
			@Param("recipient") String recipient, @Param("noOfServices") String noOfServices,
			@Param("performanceTarget") String performanceTarget);

}