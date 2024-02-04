package com.hexaware.policymanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.policymanager.dto.PoliciesDTO;
import com.hexaware.policymanager.entities.Policies;
import com.hexaware.policymanager.repository.PoliciesRepository;
@Service
public class PoliciesServiceImp implements IPoliciesService{

	@Autowired
    PoliciesRepository policyrepo;
	
	@Override
	public Policies createPolicy(PoliciesDTO policyDTO) {
		Policies policy = new Policies();
		policy.setPolicyId(policyDTO.getPolicyId());
        policy.setPolicyName(policyDTO.getPolicyName());
        policy.setPolicyDescription(policyDTO.getPolicyDescription());
        policy.setCompany(policyDTO.getCompany());
        policy.setPolicyType(policyDTO.getPolicyType());
        policy.setMaturityAmount(policyDTO.getMaturityAmount());
        policy.setInitialDeposit(policyDTO.getInitialDeposit());
        policy.setTermPeriod(policyDTO.getTermPeriod());
        policy.setTermAmount(policyDTO.getTermAmount());
        policy.setInterest(policyDTO.getInterest());
        policy.setUserPolicies(policyDTO.getUserPolicies());
       
		return policyrepo.save(policy);
	}

	@Override
	public Policies updatePolicy(PoliciesDTO policyDTO) {
		Policies policy = new Policies();
		policy.setPolicyId(policyDTO.getPolicyId());
        policy.setPolicyName(policyDTO.getPolicyName());
        policy.setPolicyDescription(policyDTO.getPolicyDescription());
        policy.setCompany(policyDTO.getCompany());
        policy.setPolicyType(policyDTO.getPolicyType());
        policy.setMaturityAmount(policyDTO.getMaturityAmount());
        policy.setInitialDeposit(policyDTO.getInitialDeposit());
        policy.setTermPeriod(policyDTO.getTermPeriod());
        policy.setTermAmount(policyDTO.getTermAmount());
        policy.setInterest(policyDTO.getInterest());
        policy.setUserPolicies(policyDTO.getUserPolicies());
		return policyrepo.save(policy);
	}

	@Override
	public void deleteByPolicyId(long policyId) {
		policyrepo.deleteById(policyId);
		
	}

	@Override
	public List<Policies> getPolicyByPolicyType(String policyType) {
	
		return policyrepo.findByPolicyType(policyType);
	}

	@Override
	public List<Policies> getPolicyByCompany(String company) {
		return policyrepo.findByCompany(company);
	}

	@Override
	public List<Policies> getByAmountLessThan(double termAmount) {
		
		return policyrepo.findBytermAmountLessThan(termAmount);
	}

	@Override
	public List<Policies> getByAmountGreaterThan(double termAmount) {

		return policyrepo.findBytermAmountGreaterThan(termAmount);
	}

	@Override
	public List<Policies> getAllPolicy() {
		return policyrepo.findAll();
	}

	

}
