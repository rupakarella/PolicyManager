package com.hexaware.policymanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.policymanager.dto.UserPoliciesDTO;
import com.hexaware.policymanager.entities.Policies;
import com.hexaware.policymanager.entities.UserPolicies;
import com.hexaware.policymanager.entities.Users;
import com.hexaware.policymanager.repository.UserPoliciesRepository;

import jakarta.transaction.Transactional;
@Service
@Transactional
public class UserPoliciesServiceImp implements IUserPoliciesService{
	@Autowired
	UserPoliciesRepository userPoliciesRepo;
	@Override
	public UserPolicies createUserPolicy(UserPoliciesDTO userpolicyDTO) {
		UserPolicies userpolicy=new UserPolicies();
		
		userpolicy.setUserPolicyId(userpolicyDTO.getUserPolicyId());
		
	        Users user = userpolicyDTO.getUser();
	        userpolicy.setUser(user);
	    
	    
	        Policies policy = userpolicyDTO.getPolicy();
	        userpolicy.setPolicy(policy);
	    
		userpolicy.setStartDate(userpolicyDTO.getStartDate());
		userpolicy.setEndDate(userpolicyDTO.getEndDate());
		userpolicy.setDurationInYears(userpolicyDTO.getDurationInYears());
		
		
		return userPoliciesRepo.save(userpolicy);
	}

	@Override
	public UserPolicies updateUserPolicy(UserPoliciesDTO userpolicyDTO) {
		
		UserPolicies userpolicy=new UserPolicies();
		Users user = new Users();
		Policies policy=new Policies();
		userpolicy.setUserPolicyId(userpolicyDTO.getUserPolicyId());
		userpolicy.setUser(new Users(userpolicyDTO.getUser().getUserId())); 
	    userpolicy.setPolicy(new Policies(userpolicyDTO.getPolicy().getPolicyId()));  
		userpolicy.setStartDate(userpolicyDTO.getStartDate());
		userpolicy.setEndDate(userpolicyDTO.getEndDate());
		userpolicy.setDurationInYears(userpolicyDTO.getDurationInYears());
		
		
		return userPoliciesRepo.save(userpolicy);
	}

	@Override
	public String deleteUserPolicyByPolicyId(long policyId) {
		userPoliciesRepo.deleteById(policyId);
		return " record deleted ";
		
	}

	@Override
	public UserPolicies getUserPolicyByPolicyId(long policyId) {
		
		return userPoliciesRepo.getByPolicyPolicyId(policyId);
	}

	@Override
	public List<UserPolicies> getUserPolicyByUserId(long userId) {
		
		return userPoliciesRepo.getByUserUserId(userId);
		
	}

	@Override
	public List<UserPolicies> getAllUserPolicies() {
		return userPoliciesRepo.findAll();
	}

	@Override
	public UserPoliciesDTO getbyUserPolicyId(long userPolicyId) {
		Optional<UserPolicies> optional = userPoliciesRepo.findById(userPolicyId); 
		UserPolicies userPolicies = null;
		UserPoliciesDTO userPoliciesDTO=new UserPoliciesDTO();
		if (optional.isPresent()) {
			userPolicies = optional.get();
	        if (userPolicies != null) {
	        	userPoliciesDTO.setUserPolicyId(userPolicies.getUserPolicyId());
	        	userPoliciesDTO.setStartDate(userPolicies.getStartDate());
	        	userPoliciesDTO.setEndDate(userPolicies.getEndDate());
	        	userPoliciesDTO.setDurationInYears(userPolicies.getDurationInYears());
	        }
	    }
	    
	    return userPoliciesDTO;
		
	}
	

}
