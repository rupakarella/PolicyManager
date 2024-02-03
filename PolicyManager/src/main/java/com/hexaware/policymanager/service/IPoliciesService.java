package com.hexaware.policymanager.service;

<<<<<<< HEAD
public interface IPoliciesService {
=======
import java.util.List;

import com.hexaware.policymanager.dto.PoliciesDTO;
import com.hexaware.policymanager.entities.Policies;

public interface IPoliciesService {
	public Policies createPolicy(PoliciesDTO policyDTO);
	public Policies updatePolicy(PoliciesDTO policyDTO);
	public void deleteByPolicyId(long policyId);
    public List<Policies> getPolicyByPolicyType(String policyType);
    public List<Policies> getPolicyByCompany(String company);
    public List<Policies> getByAmountLessThan(double amount);
    public List<Policies> getByAmountGreaterThan(double amount);

	
	public List<Policies> getAllPolicy();
>>>>>>> 1b8d09207d89d7b59b3209431beffae97408e7db

}
