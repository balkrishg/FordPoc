package com.ford.poc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ford.poc.eo.IncentiveCalculation;

@Repository
public interface IncentiveCalculationRepository extends JpaRepository<IncentiveCalculation, Long> {

}