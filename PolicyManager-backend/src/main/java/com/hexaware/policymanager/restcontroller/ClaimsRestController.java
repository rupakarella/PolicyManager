package com.hexaware.policymanager.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.hexaware.policymanager.exception.UserNotFoundException;
import com.hexaware.policymanager.exception.UserPolicyNotFoundException;
import com.hexaware.policymanager.service.IClaimsService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/claims")
public class ClaimsRestController {
	@Autowired
	IClaimsService claimsService;

	@PostMapping("/register")
	@PreAuthorize("hasAuthority('User')")
	public ResponseEntity<Claims> registerClaims(@RequestBody ClaimsDTO claimsDTO) throws UserPolicyNotFoundException {
		Claims registeredClaim = claimsService.registerClaims(claimsDTO);
		return ResponseEntity.ok(registeredClaim);
	}

	@PutMapping("/update")
	@PreAuthorize("hasAuthority('Admin')")
	public ResponseEntity<Claims> updateClaims(@RequestBody ClaimsDTO claimsDTO) throws ClaimNotFoundException {
		try {
			Claims updatedClaim = claimsService.updateClaims(claimsDTO);
			return ResponseEntity.ok(updatedClaim);
		} catch (ClaimNotFoundException e) {
			throw new ClaimNotFoundException("Claim not found: " + e.getMessage());
		}
	}

	@DeleteMapping("/delete/{claimId}")
	@PreAuthorize("hasAuthority('Admin')")
	public ResponseEntity<String> deleteClaimsById(@PathVariable int claimId) throws ClaimNotFoundException {
		String result = claimsService.deleteClaimsById(claimId);
		return ResponseEntity.ok(result);
	}

	@GetMapping("/get-by-id/{claimId}")
	@PreAuthorize("hasAnyAuthority('Admin','User')")
	public Claims getClaimsById(@PathVariable int claimId) throws ClaimNotFoundException {
		return claimsService.getClaimsById(claimId);
	}

	@GetMapping("/get-by-claim-amount/{claimAmount}")
	@PreAuthorize("hasAuthority('Admin')")
	public ResponseEntity<List<Claims>> findByClaimAmount(@PathVariable double claimAmount)
			throws ClaimNotFoundException {
		List<Claims> claims = claimsService.getAllClaimsByClaimAmount(claimAmount);
		return ResponseEntity.ok(claims);
	}

	@GetMapping("/get-by-claim-status/{claimStatus}")
	@PreAuthorize("hasAuthority('Admin')")
	public ResponseEntity<List<Claims>> findByClaimStatus(@PathVariable String claimStatus)
			throws ClaimNotFoundException {
		List<Claims> claims = claimsService.getAllClaimsByClaimStatus(claimStatus);
		return ResponseEntity.ok(claims);
	}

	@GetMapping("/get-all")
	@PreAuthorize("hasAuthority('Admin')")
	public ResponseEntity<List<Claims>> getAllClaims() {
		List<Claims> claims = claimsService.getAllClaims();
		return ResponseEntity.ok(claims);
	}

	@GetMapping("/get-by-userId/{userId}")
	@PreAuthorize("hasAuthority('User')")
	public ResponseEntity<List<Claims>> getClaimsByUserId(@PathVariable long userId) throws UserNotFoundException {
		List<Claims> claims = claimsService.getClaimsByUserId(userId);
		return ResponseEntity.ok(claims);
	}
}