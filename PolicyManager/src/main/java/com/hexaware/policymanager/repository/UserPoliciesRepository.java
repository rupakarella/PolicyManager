package com.hexaware.policymanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.policymanager.entities.UserPolicies;
import com.hexaware.policymanager.entities.Users;

public interface UserPoliciesRepository extends JpaRepository<UserPolicies, Long> {
	public UserPolicies getByPolicyPolicyId(long policyId);
	public List<UserPolicies> getByUserUserId(Users userId);

}
