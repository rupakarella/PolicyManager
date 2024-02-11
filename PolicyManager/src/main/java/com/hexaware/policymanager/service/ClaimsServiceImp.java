package com.hexaware.policymanager.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	Logger logger = LoggerFactory.getLogger(ClaimsServiceImp.class);

	@Autowired
	ClaimsRepository claimsRepo;

	@Autowired
	UserPoliciesRepository userPoliciesRepo;

	@Override
	public Claims registerClaims(ClaimsDTO claimsDTO) {
		logger.info("Registering new claim");
		Claims claims = new Claims();
		claims.setClaimDate(claimsDTO.getClaimDate());
		claims.setClaimAmount(claimsDTO.getClaimAmount());
		claims.setClaimStatus(claimsDTO.getClaimStatus());

		Optional<UserPolicies> optionalUserPolicy = userPoliciesRepo.findById(claimsDTO.getUserPolicyId());

		if (optionalUserPolicy.isPresent()) {
			UserPolicies userPolicy = optionalUserPolicy.get();
			claims.setUserPolicy(userPolicy);
			Claims savedClaim = claimsRepo.save(claims);
			logger.info("Claim registered successfully", savedClaim);
			return savedClaim;
		} else {
			logger.warn("User policy not found with ID: {0}", claimsDTO.getUserPolicyId());
			throw new UserPolicyNotFoundException("UserPolicy not found with ID: " + claimsDTO.getUserPolicyId());
		}
	}

	@Override
	public Claims updateClaims(ClaimsDTO claimsDTO) throws ClaimNotFoundException {
		logger.info("Updating claim");
		Optional<Claims> optionalClaims = claimsRepo.findById(claimsDTO.getClaimId());
		if (optionalClaims.isPresent()) {
			Claims claims = optionalClaims.get();
			claims.setClaimId(claimsDTO.getClaimId());
			claims.setClaimDate(claimsDTO.getClaimDate());
			claims.setClaimAmount(claimsDTO.getClaimAmount());
			claims.setClaimStatus(claimsDTO.getClaimStatus());
			Claims updatedClaim = claimsRepo.save(claims);
			logger.info("Claim updated successfully", updatedClaim);
			return updatedClaim;
		} else {
			logger.warn("Claim not found with ID", claimsDTO.getClaimId());
			throw new ClaimNotFoundException("Claim with ID " + claimsDTO.getClaimId() + " not found");
		}
	}

	@Override
	public String deleteClaimsById(int claimId) throws ClaimNotFoundException {
		logger.info("Deleting claim");
		Optional<Claims> optionalClaims = claimsRepo.findById(claimId);
		if (optionalClaims.isPresent()) {
			claimsRepo.deleteById(claimId);
			logger.info("Claim is deleted");
			return "Record deleted";
		} else {
			logger.warn("Claim not found with ID");
			throw new ClaimNotFoundException("Claim with ID " + claimId + " not found");
		}
	}

	@Override
	public Claims getClaimsById(int claimId) throws ClaimNotFoundException {
		logger.info("Fetching claim with ID: {0}", claimId);
		Optional<Claims> optionalClaims = claimsRepo.findById(claimId);
		if (optionalClaims.isPresent()) {
			logger.info("Claim found");
			return optionalClaims.get();
		} else {
			logger.warn("Claim not found");
			throw new ClaimNotFoundException("Claim with ID " + claimId + " not found");
		}
	}

	@Override
	public List<Claims> getAllClaims() {
		logger.info("Fetching all claims");
		return claimsRepo.findAll();
	}

	@Override
	public List<Claims> getAllClaimsByClaimAmount(double claimAmount) throws ClaimNotFoundException {
		logger.info("Fetching claims by claim amount: " + claimAmount);
		List<Claims> claims = claimsRepo.findByClaimAmount(claimAmount);
		if (claims.isEmpty()) {
			logger.warn("No claims found for claim amount");
			throw new ClaimNotFoundException("No claims found for claim amount: " + claimAmount);
		}
		return claims;
	}

	@Override
	public List<Claims> getAllClaimsByClaimStatus(String claimStatus) throws ClaimNotFoundException {
		logger.info("Fetching claims by claim status: " + claimStatus);
		List<Claims> claims = claimsRepo.findByClaimStatus(claimStatus);
		if (claims.isEmpty()) {
			logger.warn("No claims found for claim status: " + claimStatus);
			throw new ClaimNotFoundException("No claims found for claim status: " + claimStatus);
		}
		return claims;
	}

}