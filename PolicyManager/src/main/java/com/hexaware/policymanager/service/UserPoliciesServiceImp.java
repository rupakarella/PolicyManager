package com.hexaware.policymanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.policymanager.dto.UserPoliciesDTO;
import com.hexaware.policymanager.entities.Policies;
import com.hexaware.policymanager.entities.UserPolicies;
import com.hexaware.policymanager.entities.Users;
import com.hexaware.policymanager.repository.PoliciesRepository;
import com.hexaware.policymanager.repository.UserPoliciesRepository;
import com.hexaware.policymanager.repository.UsersRepository;

import jakarta.transaction.Transactional;
@Service
@Transactional
public class UserPoliciesServiceImp implements IUserPoliciesService{
	@Autowired
	UserPoliciesRepository userPoliciesRepo;
	@Autowired
    UsersRepository userRepo;  
	@Autowired
	PoliciesRepository policiesRepo;
	@Override
	public UserPolicies createUserPolicy(UserPoliciesDTO userpolicyDTO) {
		UserPolicies userpolicy=new UserPolicies();
		Users user = new Users();
    	user = userRepo.findById(userpolicyDTO.getUserId()).orElse(null);
    	userpolicy.setUser(user);
    	Policies policy=new Policies();
    	policy=policiesRepo.findById(userpolicyDTO.getPolicyId()).orElse(null);
    	userpolicy.setUserPolicyId(userpolicyDTO.getUserPolicyId());
		userpolicy.setStartDate(userpolicyDTO.getStartDate());
		userpolicy.setEndDate(userpolicyDTO.getEndDate());
		userpolicy.setDurationInYears(userpolicyDTO.getDurationInYears());
		
		
		return userPoliciesRepo.save(userpolicy);
	}

	@Override
	public UserPolicies updateUserPolicy(UserPoliciesDTO userpolicyDTO) {
		
		UserPolicies userpolicy=new UserPolicies();
		Users user = new Users();
    	user = userRepo.findById(userpolicyDTO.getUserId()).orElse(null);
    	userpolicy.setUser(user);
    	Policies policy=new Policies();
    	policy=policiesRepo.findById(userpolicyDTO.getPolicyId()).orElse(null);
    	userpolicy.setUserPolicyId(userpolicyDTO.getUserPolicyId());
		userpolicy.setStartDate(userpolicyDTO.getStartDate());
		userpolicy.setEndDate(userpolicyDTO.getEndDate());
		userpolicy.setDurationInYears(userpolicyDTO.getDurationInYears());
		
		
		return userPoliciesRepo.save(userpolicy);
	}

	@Override
	public String deleteUserPolicyById(long userPolicyId) {
		userPoliciesRepo.deleteById(userPolicyId);
		return " record deleted ";
		
	}
	
	@Override
	public List<UserPolicies> getAllUserPolicies() {
		return userPoliciesRepo.findAll();
	}

	@Override
	public UserPoliciesDTO getbyUserPolicyId(long userPolicyId) 
	{
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
