package com.ford.poc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ford.poc.eo.IncentiveContractSales;

@Repository
public interface IncentiveContractSalesRepository extends JpaRepository<IncentiveContractSales, Long>{

}
