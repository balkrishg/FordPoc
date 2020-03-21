package com.ford.poc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ford.poc.eo.IncentiveStructure;

@Repository
public interface IncentiveStructureRepository extends JpaRepository<IncentiveStructure, Long>{
    
    List<IncentiveStructure> findByProgramCode(String programCode);
	
    List<IncentiveStructure> findByProgramCodeAndProductType(String programCode, String productType);

}
