package com.ford.poc.eo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "INC_DLR_DETAILS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncentiveDealerDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DEALER_CODE", nullable = false, unique = true)
	private String dealerCode;

	@Column(name = "DEALER_NAME")
	private String dealerName;
}