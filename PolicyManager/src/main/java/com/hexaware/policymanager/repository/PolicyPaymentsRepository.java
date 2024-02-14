package com.hexaware.policymanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.policymanager.entities.PolicyPayments;

public interface PolicyPaymentsRepository extends JpaRepository<PolicyPayments, Long> {
	public List<PolicyPayments> getPaymentsByPaymentStatus(String paymentStatus);
}
