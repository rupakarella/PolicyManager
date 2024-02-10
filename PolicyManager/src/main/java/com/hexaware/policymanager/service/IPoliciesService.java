package com.hexaware.policymanager.service;

import java.util.List;

import com.hexaware.policymanager.dto.PoliciesDTO;
import com.hexaware.policymanager.entities.Policies;
import com.hexaware.policymanager.exception.PolicyNotFoundException;
import com.hexaware.policymanager.exception.PolicyRegisteredByUserException;

public interface IPoliciesService {
	public Policies createPolicy(PoliciesDTO policyDTO);
	public Policies updatePolicy(PoliciesDTO policyDTO) throws PolicyNotFoundException;
	public String deleteByPolicyId(long policyId) throws PolicyNotFoundException, PolicyRegisteredByUserException;
    public List<Policies> getPolicyByPolicyType(String policyType)throws PolicyNotFoundException;
    
    public List<Policies> getPolicyByCompany(String company)throws PolicyNotFoundException;
    public List<Policies> getBytermAmountLessThan(double termAmount)throws PolicyNotFoundException;
    public List<Policies> getBytermAmountGreaterThan(double termAmount)throws PolicyNotFoundException;

	public PoliciesDTO getbyPolicyId(long policyId) throws PolicyNotFoundException;
	public List<Policies> getAllPolicies();

}