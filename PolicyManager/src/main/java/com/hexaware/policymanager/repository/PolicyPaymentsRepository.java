package com.hexaware.policymanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.policymanager.entities.PolicyPayments;

public interface PolicyPaymentsRepository extends JpaRepository<PolicyPayments, Long> {

	public void deleteByTransactionId(long txnId);

}
