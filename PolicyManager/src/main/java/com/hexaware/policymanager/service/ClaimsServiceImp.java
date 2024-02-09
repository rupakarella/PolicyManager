package com.hexaware.policymanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.policymanager.dto.ClaimsDTO;
import com.hexaware.policymanager.entities.Claims;
import com.hexaware.policymanager.entities.UserPolicies;
import com.hexaware.policymanager.exception.ClaimNotFoundException;
import com.hexaware.policymanager.exception.UserPolicyNotFoundException;
import com.hexaware.policymanager.repository.ClaimsRepository;
import com.hexaware.policymanager.repository.UserPoliciesRepository;

import jakarta.transaction.Transactional;
@Service
@Transactional
public class ClaimsServiceImp implements IClaimsService {

	@Autowired
	ClaimsRepository claimsRepo;
	@Autowired
	UserPoliciesRepository userPoliciesRepo;
	@Override
    public Claims registerClaims(ClaimsDTO claimsDTO) {
        Claims claims = new Claims();
        claims.setClaimDate(claimsDTO.getClaimDate());
        claims.setClaimAmount(claimsDTO.getClaimAmount());
        claims.setClaimStatus(claimsDTO.getClaimStatus());

        long userPolicyId = claimsDTO.getUserPolicy().getUserPolicyId();
        Optional<UserPolicies> optionalUserPolicy = userPoliciesRepo.findById(userPolicyId);

        if (optionalUserPolicy.isPresent()) {
            UserPolicies userPolicy = optionalUserPolicy.get();
            // Set the UserPolicies object in the Claims entity
            claims.setUserPolicy(userPolicy);
            // Save the claim
            return claimsRepo.save(claims);
        } else {
            throw new UserPolicyNotFoundException("UserPolicy not found with ID: " + userPolicyId);
        }
    }

	@Override
	public Claims updateClaims(ClaimsDTO claimsDTO) {
		Optional<Claims> optionalClaims=claimsRepo.findById(claimsDTO.getClaimId());
		if(optionalClaims.isPresent())
		{
			Claims claims=optionalClaims.get();
			claims.setClaimId(claimsDTO.getClaimId());
			claims.setClaimDate(claimsDTO.getClaimDate());
			claims.setClaimAmount(claimsDTO.getClaimAmount());
			claims.setClaimStatus(claimsDTO.getClaimStatus());
			return claimsRepo.save(claims);
		}
		else {
	    	return null;
	    }
	}

	@Override
	public String deleteClaimsById(int claimId) {
		claimsRepo.deleteById(claimId);
		return "record deleted";
	}

	@Override
	public Claims getClaimsById(int claimId) throws ClaimNotFoundException {
	    Optional<Claims> optionalClaims = claimsRepo.findById(claimId);
	    
	    if (optionalClaims.isPresent()) {
	        return optionalClaims.get();
	    } else {
	        throw new ClaimNotFoundException("Claim with ID " + claimId + " not found");
	    }
	}





	@Override
	public List<Claims> getAllClaims() {
		return claimsRepo.findAll();
	}

	@Override
	public List<Claims> getAllClaimsByClaimAmount(double claimAmount) {
		return claimsRepo.findByClaimAmount(claimAmount);
	}

	@Override
	public List<Claims> getAllClaimsByClaimStatus(String claimStatus) {
		return claimsRepo.findByClaimStatus(claimStatus);
	}

}