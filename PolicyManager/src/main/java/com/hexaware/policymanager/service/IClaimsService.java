package com.hexaware.policymanager.service;

import java.util.List;

import com.hexaware.policymanager.dto.ClaimsDTO;
import com.hexaware.policymanager.entities.Claims;
import com.hexaware.policymanager.exception.ClaimNotFoundException;

public interface IClaimsService {
	public Claims registerClaims(ClaimsDTO claimsDTO);
	public Claims updateClaims(ClaimsDTO claimsDTO);
	public String deleteClaimsById(int claimId);
	public Claims getClaimsById(int claimId) throws ClaimNotFoundException;
	public List<Claims> getAllClaims();
	public List<Claims> getAllClaimsByClaimAmount(double claimAmount);
	public List<Claims> getAllClaimsByClaimStatus(String claimStatus);
	
}