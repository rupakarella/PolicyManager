package com.hexaware.policymanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hexaware.policymanager.entities.UserPolicies;
import com.hexaware.policymanager.entities.Users;
import com.hexaware.policymanager.repository.UserPoliciesRepository;

public class UserPoliciesServiceImp implements IUserPoliciesService{
	@Autowired
	UserPoliciesRepository userPoliciesRepo;
	@Override
	public UserPolicies createUserPolicy(UserPolicies userpolicy) {
		
		return userPoliciesRepo.save(userpolicy);
	}

	@Override
	public UserPolicies updateUserPolicy(UserPolicies userpolicy) {
		
		return userPoliciesRepo.save(userpolicy);
	}

	@Override
	public String deleteUserPolicyByPolicyId(long policyId) {
		userPoliciesRepo.deleteById(policyId);
		return " record deleted ";
		
	}

	@Override
	public UserPolicies getUserPolicyByPolicyId(long policyId) {
		
		return userPoliciesRepo.getByPolicyId(policyId);
	}

	@Override
	public List<UserPolicies> getUserPolicyByUserId(Users userId) {
		return userPoliciesRepo.getByUserId(userId);
		
	}

	@Override
	public List<UserPolicies> getAllUserPolicy() {
		return userPoliciesRepo.findAll();
	}
	

}
