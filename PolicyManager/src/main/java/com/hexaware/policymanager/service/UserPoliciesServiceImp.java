package com.hexaware.policymanager.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.policymanager.dto.UserPoliciesDTO;
import com.hexaware.policymanager.entities.Policies;
import com.hexaware.policymanager.entities.UserPolicies;
import com.hexaware.policymanager.entities.Users;
import com.hexaware.policymanager.exception.PolicyNotFoundException;
import com.hexaware.policymanager.exception.UserNotFoundException;
import com.hexaware.policymanager.exception.UserPolicyNotFoundException;
import com.hexaware.policymanager.repository.ClaimsRepository;
import com.hexaware.policymanager.repository.PoliciesRepository;
import com.hexaware.policymanager.repository.UserPoliciesRepository;
import com.hexaware.policymanager.repository.UsersRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserPoliciesServiceImp implements IUserPoliciesService {

	Logger logger=LoggerFactory.getLogger(UserPoliciesServiceImp.class);
	
	@Autowired
	UserPoliciesRepository userPoliciesRepo;

	@Autowired
	UsersRepository userRepo;

	@Autowired
	PoliciesRepository policiesRepo;

	@Autowired
	ClaimsRepository claimsRepo;

	@Override
	public UserPolicies createUserPolicy(UserPoliciesDTO userpolicyDTO)throws UserNotFoundException,PolicyNotFoundException {
	    logger.info("Creating user policy for User ID: {} and Policy ID: {}", userpolicyDTO.getUserId(), userpolicyDTO.getPolicyId());
		Optional<Users> optionalUser = userRepo.findById(userpolicyDTO.getUserId());
	    if (optionalUser.isEmpty()) {
	    	logger.error("User not found with ID: " + userpolicyDTO.getUserId());
	    	throw new UserNotFoundException("User not found with ID: " + userpolicyDTO.getUserId());
	    }
	    Users user = optionalUser.get();
	    Optional<Policies> optionalPolicy = policiesRepo.findById(userpolicyDTO.getPolicyId());
	    if (optionalPolicy.isEmpty()) {
	    	logger.error("Policy not found with ID:{}", userpolicyDTO.getPolicyId());
	    	throw new PolicyNotFoundException("Policy not found with ID: " + userpolicyDTO.getPolicyId());
	    }
	    Policies policy = optionalPolicy.get();
	    UserPolicies userpolicy = new UserPolicies();
	    userpolicy.setStartDate(userpolicyDTO.getStartDate());
	    userpolicy.setUser(user);
	    userpolicy.setPolicy(policy);
	    userpolicy.setDurationInYears(userpolicyDTO.getDurationInYears());
	    logger.info("User policy created successfully");
	    return userPoliciesRepo.save(userpolicy);
	}
	
	@Override
	public UserPolicies updateUserPolicy(UserPoliciesDTO userpolicyDTO)throws UserPolicyNotFoundException {

		logger.info("Updating user policy with ID: {}", userpolicyDTO.getUserPolicyId());
		Optional<UserPolicies> optionalUserPolicy = userPoliciesRepo.findById(userpolicyDTO.getUserPolicyId());
        if (optionalUserPolicy.isPresent()) {
            UserPolicies userpolicy = optionalUserPolicy.get();
            userpolicy.setStartDate(userpolicyDTO.getStartDate());
            userpolicy.setDurationInYears(userpolicyDTO.getDurationInYears());
            UserPolicies updatedUserPolicy = userPoliciesRepo.save(userpolicy);

            logger.info("User policy updated successfully");
            return updatedUserPolicy;
        } else {
            logger.error("User policy not found with ID: {}", userpolicyDTO.getUserPolicyId());
            throw new UserPolicyNotFoundException("User policy not found with ID: " + userpolicyDTO.getUserPolicyId());
        }
    }

	@Override
	public String deleteUserPolicyById(long userPolicyId) throws UserPolicyNotFoundException{
		logger.info("Deleting user policy with ID: {}", userPolicyId);
		if (userPoliciesRepo.existsById(userPolicyId)) {
            userPoliciesRepo.deleteById(userPolicyId);
            logger.info("User policy deleted successfully");
            return "Record deleted";
        } else {
            logger.error("User policy not found with ID: {}", userPolicyId);
            throw new UserPolicyNotFoundException("User policy not found with ID: " + userPolicyId);
        }
    }
	
	@Override
	public UserPoliciesDTO getbyUserPolicyId(long userPolicyId) throws UserPolicyNotFoundException {
	    logger.info("Fetching user policy with ID: {}", userPolicyId);

	    Optional<UserPolicies> optionalUserPolicy = userPoliciesRepo.findById(userPolicyId);
	    if (optionalUserPolicy.isPresent()) {
	        UserPolicies userPolicies = optionalUserPolicy.get();
	        UserPoliciesDTO userPoliciesDTO = new UserPoliciesDTO();
	        userPoliciesDTO.setUserPolicyId(userPolicies.getUserPolicyId());
	        userPoliciesDTO.setStartDate(userPolicies.getStartDate());
	        userPoliciesDTO.setDurationInYears(userPolicies.getDurationInYears());
	        
	        if (userPolicies.getUser() != null) {
	            userPoliciesDTO.setUserId(userPolicies.getUser().getUserId());
	        }
	        
	        if (userPolicies.getPolicy() != null) {
	            userPoliciesDTO.setPolicyId(userPolicies.getPolicy().getPolicyId());
	            // Set other policy properties if needed
	        }

	        return userPoliciesDTO;
	    } else {
	        logger.error("User policy not found with ID: {}", userPolicyId);
	        throw new UserPolicyNotFoundException("User policy not found with ID: " + userPolicyId);
	    }
	}


	@Override
	public List<UserPolicies> getAllUserPolicies() {
		logger.info("Fetching all user policies");
		List<UserPolicies> userPolicies = userPoliciesRepo.findAll();
		logger.info("Fetched {} user policies", userPolicies.size());
		return userPolicies;
	}

	
	
	@Override
    public List<UserPolicies> getUserPoliciesByUserId(long userId)throws UserNotFoundException {
		logger.info("Fetching user policies for User ID: {}", userId);
		List<UserPolicies> userPolicies = userPoliciesRepo.getByUserUserId(userId);
		if (userPolicies.isEmpty()) {
            logger.error("User policies not found for User ID: {}", userId);
            throw new UserNotFoundException("User policies not found for User ID: " + userId);
        }
        logger.info("Fetched {} user policies for User ID: {}", userPolicies.size(), userId);
        return userPolicies;
    }
}