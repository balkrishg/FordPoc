package com.ford.poc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ford.poc.eo.IncentiveProgram;

@Repository
public interface IncentiveProgramRepository extends JpaRepository<IncentiveProgram, String>{

}
