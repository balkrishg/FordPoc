package com.ford.poc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ford.poc.eo.IncentiveDealerDetails;

@Repository
public interface IncentiveDealerDetailsRepository extends JpaRepository<IncentiveDealerDetails, String>{

}
