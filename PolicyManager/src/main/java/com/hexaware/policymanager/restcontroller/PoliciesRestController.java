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
import com.hexaware.policymanager.service.IPoliciesService;

@RestController
@RequestMapping("/api/policies")
public class PoliciesRestController {
	
	@Autowired
	IPoliciesService services;
	
	@PostMapping(value="/add")
	public Policies addPolicy(@RequestBody PoliciesDTO policyDTO)
	{
		return services.createPolicy(policyDTO);
	}
	
	@PutMapping(value="/update")
	public Policies updatePolicy(@RequestBody PoliciesDTO policyDTO)
	{
		return services.updatePolicy(policyDTO);
	}
	
	@DeleteMapping(value="/delete/{policyId}")
	public void deletePolicy(@PathVariable Long policyId)
	{
		 services.deleteByPolicyId(policyId);
	}
	
	@GetMapping(value="/get/policytype/{policyType}")
	public List<Policies> getPoliciesByPolicyType(@PathVariable String policyType)
	{
		return services.getPolicyByPolicyType(policyType);
	}
	
	@GetMapping(value="/get/company/{company}")
	public List<Policies> getPoliciesByCompany(@PathVariable String company)
	{
		return services.getPolicyByCompany(company);
	}
	
	@GetMapping(value="/get/termamountgt/{termamount}")
	public List<Policies> getByAmountGreaterThan(@PathVariable long termamount)
	{
		return services.getBytermAmountGreaterThan(termamount);
	}
	
	@GetMapping(value="/get/termamountlwr/{termamount}")
	public List<Policies> getByAmountLessThan(@PathVariable long termamount)
	{
		return services.getBytermAmountLessThan(termamount);
	}
	

}
