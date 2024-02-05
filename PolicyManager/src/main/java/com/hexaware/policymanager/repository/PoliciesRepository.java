package com.hexaware.policymanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.policymanager.entities.Policies;

public interface PoliciesRepository extends JpaRepository<Policies, Long> {

	public List<Policies> findByCompany(String company);

	List<Policies> findBytermAmountLessThan(double termAmount);

	List<Policies> findBytermAmountGreaterThan(double termAmount);

	List<Policies> findByPolicyType(String policyType);

}