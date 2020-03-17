package com.ford.poc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ford.poc.eo.IncentiveStructure;

@Repository
public interface IncentiveStructureRepository extends JpaRepository<IncentiveStructure, Long>{
	
	@Query(value="select * from INC_PRD_STRUCTURE i where i.PROGRAM_CODE=:programCode and i.PRODUCT_TYPE=:productType", nativeQuery=true)
    List<IncentiveStructure> findByProgramCodeAndProductType(@Param("programCode") String programCode, @Param("productType") String productType);
}
