package com.hexaware.policymanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.policymanager.entities.UserPolicies;

public interface UserPoliciesRepository extends JpaRepository<UserPolicies, Long> {

}
