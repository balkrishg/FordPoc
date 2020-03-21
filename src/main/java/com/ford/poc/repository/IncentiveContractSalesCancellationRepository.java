package com.ford.poc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ford.poc.eo.IncentiveContractSalesCancellation;

@Repository
public interface IncentiveContractSalesCancellationRepository extends JpaRepository<IncentiveContractSalesCancellation, Long>{

}
