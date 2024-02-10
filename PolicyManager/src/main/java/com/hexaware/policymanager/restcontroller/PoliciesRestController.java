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

import com.hexaware.policymanager.dto.PoliciesDTO;
import com.hexaware.policymanager.entities.Policies;
import com.hexaware.policymanager.exception.PolicyNotFoundException;
import com.hexaware.policymanager.exception.PolicyRegisteredByUserException;
import com.hexaware.policymanager.service.IPoliciesService;

@RestController
@RequestMapping("/api/policies")
public class PoliciesRestController {

	@Autowired
	IPoliciesService services;

	@PostMapping(value = "/add")
	public Policies addPolicy(@RequestBody PoliciesDTO policyDTO) {
		return services.createPolicy(policyDTO);
	}

	@PutMapping(value = "/update")
	public Policies updatePolicy(@RequestBody PoliciesDTO policyDTO) throws PolicyNotFoundException {
		return services.updatePolicy(policyDTO);
	}

	@DeleteMapping(value = "/delete/{policyId}")
	public String deletePolicy(@PathVariable Long policyId) throws PolicyNotFoundException, PolicyRegisteredByUserException {
		services.deleteByPolicyId(policyId);
		return "PolicyDeleted";
	}

	@GetMapping(value = "/get/policyType/{policyType}")
	public List<Policies> getPoliciesByPolicyType(@PathVariable String policyType) throws PolicyNotFoundException {
		return services.getPolicyByPolicyType(policyType);
	}

	@GetMapping(value = "/get/company/{company}")
	public List<Policies> getPoliciesByCompany(@PathVariable String company) throws PolicyNotFoundException {
		return services.getPolicyByCompany(company);
	}

	@GetMapping(value = "/get/term-amount-greater/{termAmount}")
	public List<Policies> getByAmountGreaterThan(@PathVariable long termAmount) throws PolicyNotFoundException {
		return services.getBytermAmountGreaterThan(termAmount);
	}

	@GetMapping(value = "/get/term-amount-lower/{termAmount}")
	public List<Policies> getByAmountLessThan(@PathVariable long termAmount) throws PolicyNotFoundException {
		return services.getBytermAmountLessThan(termAmount);
	}

	@GetMapping("/get-all")
	public List<Policies> getAllPolicies() {
		return services.getAllPolicies();
	}

}