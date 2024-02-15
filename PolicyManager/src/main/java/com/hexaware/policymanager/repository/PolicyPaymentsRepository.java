package com.hexaware.policymanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.policymanager.entities.PolicyPayments;

@Repository
public interface PolicyPaymentsRepository extends JpaRepository<PolicyPayments, Long> {
	public List<PolicyPayments> getPaymentsByPaymentStatus(String paymentStatus);

}
