package com.hexaware.policymanager.service;

import java.util.List;

import com.hexaware.policymanager.dto.UserPoliciesDTO;
import com.hexaware.policymanager.entities.UserPolicies;
import com.hexaware.policymanager.exception.PolicyNotFoundException;
import com.hexaware.policymanager.exception.UserNotFoundException;
import com.hexaware.policymanager.exception.UserPolicyNotFoundException;

public interface IUserPoliciesService {
	
	public UserPolicies createUserPolicy(UserPoliciesDTO userpolicyDTO)
			throws UserNotFoundException, PolicyNotFoundException;

	public UserPolicies updateUserPolicy(UserPoliciesDTO userpolicyDTO) throws UserPolicyNotFoundException;

	public String deleteUserPolicyById(long userPolicyId) throws UserPolicyNotFoundException;

	public List<UserPolicies> getAllUserPolicies();

	public UserPolicies getbyUserPolicyId(long userPolicyid) throws UserPolicyNotFoundException;

	public List<UserPolicies> getUserPoliciesByUserId(long userId) throws UserNotFoundException;

}