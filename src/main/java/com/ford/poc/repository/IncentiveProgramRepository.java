package com.ford.poc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ford.poc.eo.IncentiveProgram;

@Repository
public interface IncentiveProgramRepository extends JpaRepository<IncentiveProgram, String> {

//	@Query(value = "SELECT * FROM INC_PGM_DATA where to_char(DATE_FROM, 'MONYY') = :targetMonth OR to_char(DATE_TO, 'MONYY') = :targetMonth", nativeQuery = true)
//	List<IncentiveProgram> findByTargetMonth(@Param("targetMonth") String targetMonth);
}
