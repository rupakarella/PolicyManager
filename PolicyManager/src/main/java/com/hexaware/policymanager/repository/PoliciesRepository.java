package com.hexaware.policymanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.policymanager.entities.Policies;

public interface PoliciesRepository extends JpaRepository<Policies, Long> {

	List<Policies> findByCompany(String company);

	List<Policies> findByAmountLessThan(double amount);

	List<Policies> findByAmountGreaterThan(double amount);

	List<Policies> findByPolicyType(String policyType);

}
