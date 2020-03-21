package com.ford.poc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ford.poc.eo.IncentiveDealerTarget;

@Repository
public interface IncentiveDealerTargetRepository extends JpaRepository<IncentiveDealerTarget, Long>{

}
