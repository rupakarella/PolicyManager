package com.hexaware.policymanager.service;

import java.util.List;

import com.hexaware.policymanager.dto.ClaimsDTO;
import com.hexaware.policymanager.entities.Claims;
import com.hexaware.policymanager.exception.ClaimNotFoundException;
import com.hexaware.policymanager.exception.UserPolicyNotFoundException;

public interface IClaimsService {
	public Claims registerClaims(ClaimsDTO claimsDTO) throws UserPolicyNotFoundException;

	public Claims updateClaims(ClaimsDTO claimsDTO) throws ClaimNotFoundException;

	public String deleteClaimsById(int claimId) throws ClaimNotFoundException;

	public Claims getClaimsById(int claimId) throws ClaimNotFoundException;

	public List<Claims> getAllClaims();

	public List<Claims> getAllClaimsByClaimAmount(double claimAmount) throws ClaimNotFoundException;

	public List<Claims> getAllClaimsByClaimStatus(String claimStatus) throws ClaimNotFoundException;
	
	List<Claims> getClaimsByUserId(long userId) throws UserPolicyNotFoundException;

}