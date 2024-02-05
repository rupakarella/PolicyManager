package com.hexaware.policymanager.service;

import java.util.List;

import com.hexaware.policymanager.dto.PoliciesDTO;
import com.hexaware.policymanager.entities.Policies;

public interface IPoliciesService {
	public Policies createPolicy(PoliciesDTO policyDTO);
	public Policies updatePolicy(PoliciesDTO policyDTO);
	public Policies deleteByPolicyId(long policyId);
    public List<Policies> getPolicyByPolicyType(String policyType);
    
    public List<Policies> getPolicyByCompany(String company);
    public List<Policies> getBytermAmountLessThan(double termAmount);
    public List<Policies> getBytermAmountGreaterThan(double termAmount);

	public PoliciesDTO getbyPolicyId(long policyId);
	public List<Policies> getAllPolicies();

}
