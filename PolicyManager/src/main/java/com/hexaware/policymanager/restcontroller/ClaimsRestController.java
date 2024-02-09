package com.hexaware.policymanager.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.policymanager.dto.ClaimsDTO;
import com.hexaware.policymanager.entities.Claims;
import com.hexaware.policymanager.exception.ClaimNotFoundException;
import com.hexaware.policymanager.service.IClaimsService;

@RestController
@RequestMapping("/api/claims")
public class ClaimsRestController {
	@Autowired
	IClaimsService claimsService;
	
	@PostMapping("/register-claims")
	public Claims registerClaims(@RequestBody ClaimsDTO claimsDTO)
	{
		return claimsService.registerClaims(claimsDTO);
	}
	@PutMapping("/update-claims")
	public Claims updateClaims(@RequestBody ClaimsDTO claimsDTO)
	{
		return claimsService.updateClaims(claimsDTO);
	}
	@DeleteMapping("/delete-claims-by-id/{claimId}")
	public String deleteClaimsById(@PathVariable int claimId)
	{
		return claimsService.deleteClaimsById(claimId);
	}
	
	@GetMapping("/get-claims-by-id/{claimId}")
	public Claims getClaimsById(@PathVariable int claimId) throws ClaimNotFoundException
	{
		return claimsService.getClaimsById(claimId);
	}
	
	@GetMapping("/find-by-claim-amount/{claimAmount}")
	public List<Claims> findByClaimAmount(@PathVariable double claimAmount)
	{
		return claimsService.getAllClaimsByClaimAmount(claimAmount);
	}
	@GetMapping("/find-by-claim-status/{claimStatus}")
	public List<Claims> findByClaimStatus(@PathVariable String claimStatus)
	{
		return claimsService.getAllClaimsByClaimStatus(claimStatus);
	}
	@GetMapping("/get-all-claims")
	public List<Claims> getAllClaims()
	{
		return claimsService.getAllClaims();
	}
}