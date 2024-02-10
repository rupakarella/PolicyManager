package com.hexaware.policymanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.policymanager.entities.UserPolicies;
import com.hexaware.policymanager.entities.Users;
@Repository
public interface UserPoliciesRepository extends JpaRepository<UserPolicies, Long> {

	
	public List<UserPolicies> getByUserUserId(long userId);
}
