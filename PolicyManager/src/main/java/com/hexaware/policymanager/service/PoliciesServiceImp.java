package com.hexaware.policymanager.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.policymanager.dto.PoliciesDTO;
import com.hexaware.policymanager.dto.UserPoliciesDTO;
import com.hexaware.policymanager.entities.Policies;
import com.hexaware.policymanager.entities.UserPolicies;
import com.hexaware.policymanager.exception.PolicyNotFoundException;
import com.hexaware.policymanager.repository.PoliciesRepository;
import com.hexaware.policymanager.repository.UserPoliciesRepository;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class PoliciesServiceImp implements IPoliciesService {

	Logger logger = LoggerFactory.getLogger(PoliciesServiceImp.class);

	@Autowired
	PoliciesRepository policyrepo;

	@Autowired
	UserPoliciesRepository userRepo;

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

	    Policies savedPolicy = policyrepo.save(policy);

	    List<UserPolicies> userPoliciesList = new ArrayList<>();
	    for (UserPolicies userPolicyDTO : policyDTO.getUserPolicies()) {
	        UserPolicies userPolicies = new UserPolicies();
	        userPolicies.setPolicy(savedPolicy);
	        userPolicies.setStartDate(userPolicyDTO.getStartDate());
	        userPolicies.setDurationInYears(userPolicyDTO.getDurationInYears());
	        userPoliciesList.add(userPolicies);
	        userRepo.save(userPolicies);
	    }

	    savedPolicy.setUserPolicies(userPoliciesList);
	    policyrepo.save(savedPolicy);
	    return savedPolicy;
	}



	@Override
	public Policies updatePolicy(PoliciesDTO policyDTO) {

		try {
			Optional<Policies> Policy = policyrepo.findById(policyDTO.getPolicyId());
			if (Policy.isPresent()) {
				Policies existingPolicy = Policy.get();
				existingPolicy.setPolicyName(policyDTO.getPolicyName());
				existingPolicy.setPolicyDescription(policyDTO.getPolicyDescription());
				existingPolicy.setCompany(policyDTO.getCompany());
				existingPolicy.setPolicyType(policyDTO.getPolicyType());
				existingPolicy.setMaturityAmount(policyDTO.getMaturityAmount());
				existingPolicy.setInitialDeposit(policyDTO.getInitialDeposit());
				existingPolicy.setTermPeriod(policyDTO.getTermPeriod());
				existingPolicy.setTermAmount(policyDTO.getTermAmount());
				existingPolicy.setInterest(policyDTO.getInterest());
				existingPolicy.setUserPolicies(policyDTO.getUserPolicies());

				logger.info("Policy Updated successfully");
				return policyrepo.save(existingPolicy);
			} else {
				throw new PolicyNotFoundException("Policy not found with ID: " + policyDTO.getPolicyId());
			}
		} catch (Exception e) {
			throw new RuntimeException("Error updating policy", e);
		}
	}

	@Override
	public Policies deleteByPolicyId(long policyId) {
		try {
			Policies deletedPolicy = policyrepo.findById(policyId).orElse(null);
			policyrepo.deleteById(policyId);

			logger.info("Policy deleted succesfully with ID: {}" + policyId);
			return deletedPolicy;

		} catch (PolicyNotFoundException e) {
			throw e;
		}
	}

	@Override
	public List<Policies> getPolicyByPolicyType(String policyType) throws PolicyNotFoundException {

		List<Policies> policies = policyrepo.findByPolicyType(policyType);

		if (policies.isEmpty()) {
			throw new PolicyNotFoundException("No policies found for policy type: " + policyType);
		}
		logger.info("Policies retrieved by policy type '{}': {}", policyType, policies);

		return policies;

	}

	@Override
	public List<Policies> getPolicyByCompany(String company) {
		List<Policies> policies = policyrepo.findByCompany(company);
		if (policies.isEmpty()) {
			throw new PolicyNotFoundException("No policies found for company: " + company);
		}
		logger.info("Policies retrieved by company '{}': {}", company, policies);
		return policies;
	}

	@Override
	public List<Policies> getBytermAmountLessThan(double termAmount) {
		List<Policies> policies = policyrepo.findBytermAmountLessThan(termAmount);
        if (policies.isEmpty()) {
            throw new PolicyNotFoundException("No policies found with term amount less than: " + termAmount);
        }
        logger.info("Policies retrieved with term amount less than '{}': {}", termAmount, policies);
        return policies;
    }
	
	@Override
	public List<Policies> getBytermAmountGreaterThan(double termAmount) {
		List<Policies> policies = policyrepo.findBytermAmountGreaterThan(termAmount);
        if (policies.isEmpty()) {
            throw new PolicyNotFoundException("No policies found with term amount greater than: " + termAmount);
        }
        logger.info("Policies retrieved with term amount greater than '{}': {}", termAmount, policies);
        return policies;
    }

	@Override
	public List<Policies> getAllPolicies() {
		List<Policies> policies = policyrepo.findAll();
		logger.info("Retrieved all policies successfully: {}", policies);
		return policies;
	}

	@Override
	public PoliciesDTO getbyPolicyId(long policyId) throws PolicyNotFoundException {

		Optional<Policies> optional = policyrepo.findById(policyId);
		if (!optional.isPresent()) {
			throw new PolicyNotFoundException("Policy not found with ID: " + policyId);
		}
		Policies policies = optional.get();
		PoliciesDTO policiesDTO = new PoliciesDTO();
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
		logger.info("Retrieved policy by ID '{}': {}", policyId, policiesDTO);
		return policiesDTO;
	}
}
