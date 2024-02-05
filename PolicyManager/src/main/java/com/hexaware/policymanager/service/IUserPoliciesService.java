package com.hexaware.policymanager.service;

import java.util.List;

import com.hexaware.policymanager.dto.UserPoliciesDTO;
import com.hexaware.policymanager.dto.UsersDTO;
import com.hexaware.policymanager.entities.UserPolicies;
import com.hexaware.policymanager.entities.Users;

public interface IUserPoliciesService {
	public UserPolicies createUserPolicy(UserPoliciesDTO userpolicyDTO);
	public UserPolicies updateUserPolicy(UserPoliciesDTO userpolicyDTO);
	public String deleteUserPolicyById(long userPolicyId);
	public List<UserPolicies> getAllUserPolicies();
	public UserPoliciesDTO getbyUserPolicyId(long userPolicyid);
	

}