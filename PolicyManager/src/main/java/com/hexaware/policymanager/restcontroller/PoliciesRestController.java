package com.hexaware.policymanager.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.hexaware.policymanager.dto.PoliciesDTO;
import com.hexaware.policymanager.entities.Policies;
import com.hexaware.policymanager.exception.PolicyNotFoundException;
import com.hexaware.policymanager.exception.PolicyRegisteredByUserException;
import com.hexaware.policymanager.service.IPoliciesService;

@RestController
@RequestMapping("/api/v1/policies")
public class PoliciesRestController {

	@Autowired
	IPoliciesService policiesService;

	@PostMapping(value = "/register")
	@PreAuthorize("hasAuthority('Admin')")
	public Policies addPolicy(@RequestBody PoliciesDTO policyDTO) {
		return policiesService.createPolicy(policyDTO);
	}

	@PutMapping(value = "/update")
	@PreAuthorize("hasAuthority('Admin')")
	public Policies updatePolicy(@RequestBody PoliciesDTO policyDTO) throws PolicyNotFoundException {
		return policiesService.updatePolicy(policyDTO);
	}

	@DeleteMapping(value = "/delete/{policyId}")
	@PreAuthorize("hasAuthority('Admin')")
	public String deletePolicy(@PathVariable Long policyId)
			throws PolicyNotFoundException, PolicyRegisteredByUserException {
		policiesService.deleteByPolicyId(policyId);
		return "PolicyDeleted";
	}

	@GetMapping(value = "/get-policyType/{policyType}")
	public List<Policies> getPoliciesByPolicyType(@PathVariable String policyType) throws PolicyNotFoundException {
		return policiesService.getPolicyByPolicyType(policyType);
	}

	@GetMapping(value = "/get-company/{company}")
	@PreAuthorize("hasAnyAuthority('Admin','User')")
	public List<Policies> getPoliciesByCompany(@PathVariable String company) throws PolicyNotFoundException {
		return policiesService.getPolicyByCompany(company);
	}

	@GetMapping(value = "/get-term-amount-greater/{termAmount}")
	@PreAuthorize("hasAnyAuthority('Admin','User')")
	public List<Policies> getByAmountGreaterThan(@PathVariable long termAmount) throws PolicyNotFoundException {
		return policiesService.getBytermAmountGreaterThan(termAmount);
	}

	@GetMapping(value = "/get-term-amount-lower/{termAmount}")
	@PreAuthorize("hasAnyAuthority('Admin','User')")
	public List<Policies> getByAmountLessThan(@PathVariable long termAmount) throws PolicyNotFoundException {
		return policiesService.getBytermAmountLessThan(termAmount);
	}

	@GetMapping("/get-all")
	public List<Policies> getAllPolicies() {
		return policiesService.getAllPolicies();
	}

}