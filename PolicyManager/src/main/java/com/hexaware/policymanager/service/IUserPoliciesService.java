package com.hexaware.policymanager.service;

import java.util.List;

import com.hexaware.policymanager.entities.UserPolicies;
import com.hexaware.policymanager.entities.Users;

public interface IUserPoliciesService {
	public UserPolicies createUserPolicy(UserPolicies userpolicy);
	public UserPolicies updateUserPolicy(UserPolicies userpolicy);
	public String deleteUserPolicyByPolicyId(long policyId);
	public UserPolicies getUserPolicyByPolicyId(long policyId);
	public List<UserPolicies> getUserPolicyByUserId(Users userId);
	public List<UserPolicies> getAllUserPolicy();
}
