package com.hexaware.policymanager.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.policymanager.dto.PoliciesDTO;
import com.hexaware.policymanager.entities.Policies;
import com.hexaware.policymanager.repository.PoliciesRepository;

@Service
public class PoliciesServiceImp implements IPoliciesService {

	Logger logger = LoggerFactory.getLogger(PoliciesServiceImp.class);

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
	public Policies deleteByPolicyId(long policyId) {
		Policies deletedPolicy = policyrepo.findById(policyId).orElse(null);
		policyrepo.deleteById(policyId);

		logger.info("Policy deleted succesfully with ID: {}" + policyId);
		return deletedPolicy;

	}

	@Override
	public List<Policies> getPolicyByPolicyType(String policyType) {

		List<Policies> policies = policyrepo.findByPolicyType(policyType);

		logger.info("Policy retrived by policy type: {}", policies);

		return policies;

	}

	@Override
	public List<Policies> getPolicyByCompany(String company) {
		return policyrepo.findByCompany(company);
	}

	@Override
	public List<Policies> getBytermAmountLessThan(double termAmount) {

		return policyrepo.findBytermAmountLessThan(termAmount);
	}

	@Override
	public List<Policies> getBytermAmountGreaterThan(double termAmount) {

		return policyrepo.findBytermAmountGreaterThan(termAmount);
	}

	@Override
	public List<Policies> getAllPolicies() {

		List<Policies> policies = policyrepo.findAll();
		logger.info("retrived all policies succesfully" + policies);
		return policies;
	}

	@Override
	public PoliciesDTO getbyPolicyId(long policyId) {
		
		 Optional<Policies> optional= policyrepo.findById(policyId);
		 Policies policies = null;
		PoliciesDTO policiesDTO=new PoliciesDTO();
			if (optional.isPresent()) {
				policies = optional.get();
		        if (policies != null) {
		        	policiesDTO.setPolicyId(policies.getPolicyId());
		        	policiesDTO.setPolicyName(policies.getPolicyName());
		        	policiesDTO.setPolicyDescription(policies.getPolicyDescription());
		        	policiesDTO.setCompany(policies.getCompany());
		        	policiesDTO.setPolicyType(policies.getPolicyType());
		        	policiesDTO.setMaturityAmount(policies.getMaturityAmount());
		        	policiesDTO.setInitialDeposit(policies.getInitialDeposit());
		        	policiesDTO.setTermPeriod(policies.getTermPeriod());
		        	policiesDTO.setTermAmount(policies.getTermAmount());
		        	policiesDTO.setInterest(policies.getInterest());
		        }
		    }
		    return policiesDTO;
			
		}
}