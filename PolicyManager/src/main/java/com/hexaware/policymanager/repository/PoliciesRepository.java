package com.hexaware.policymanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.policymanager.entities.Policies;

public interface PoliciesRepository extends JpaRepository<Policies, Long> {

}
