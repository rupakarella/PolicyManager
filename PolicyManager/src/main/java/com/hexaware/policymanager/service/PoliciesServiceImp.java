package com.hexaware.policymanager.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.policymanager.dto.PoliciesDTO;
import com.hexaware.policymanager.entities.Policies;
import com.hexaware.policymanager.entities.UserPolicies;
import com.hexaware.policymanager.exception.PolicyNotFoundException;
import com.hexaware.policymanager.exception.PolicyRegisteredByUserException;
import com.hexaware.policymanager.repository.PoliciesRepository;
import com.hexaware.policymanager.repository.UserPoliciesRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PoliciesServiceImp implements IPoliciesService {

	Logger logger = LoggerFactory.getLogger(PoliciesServiceImp.class);

	@Autowired
	PoliciesRepository policiesRepo;

	@Autowired
	UserPoliciesRepository userPoliciesRepo;

	@Override
	public Policies createPolicy(PoliciesDTO policyDTO) {
		logger.info("Creating new policy");
		Policies policy = new Policies();
		policy.setPolicyId(policyDTO.getPolicyId());
		policy.setPolicyName(policyDTO.getPolicyName());
		policy.setPolicyDescription(policyDTO.getPolicyDescription());
		policy.setCompany(policyDTO.getCompany());
		policy.setPolicyType(policyDTO.getPolicyType());
		policy.setInitialDeposit(policyDTO.getInitialDeposit());
		policy.setTermPeriod(policyDTO.getTermPeriod());
		policy.setTermAmount(policyDTO.getTermAmount());
		policy.setInterest(policyDTO.getInterest());
		policy.setEligibleUserTypes(policyDTO.getEligibleUserTypes());

		return policiesRepo.save(policy);
	}

	@Override
	public Policies updatePolicy(PoliciesDTO policyDTO) throws PolicyNotFoundException {
		logger.info("Updating policy");
		Optional<Policies> policy = policiesRepo.findById(policyDTO.getPolicyId());
		if (policy.isPresent()) {
			Policies existingPolicy = policy.get();
			existingPolicy.setPolicyName(policyDTO.getPolicyName());
			existingPolicy.setPolicyDescription(policyDTO.getPolicyDescription());
			existingPolicy.setCompany(policyDTO.getCompany());
			existingPolicy.setPolicyType(policyDTO.getPolicyType());
			existingPolicy.setInitialDeposit(policyDTO.getInitialDeposit());
			existingPolicy.setTermPeriod(policyDTO.getTermPeriod());
			existingPolicy.setTermAmount(policyDTO.getTermAmount());
			existingPolicy.setInterest(policyDTO.getInterest());
			existingPolicy.setEligibleUserTypes(policyDTO.getEligibleUserTypes());
			logger.info("Policy Updated Successfully");
			return policiesRepo.save(existingPolicy);
		} else
			logger.error("PolicyId not found");
		throw new PolicyNotFoundException("Policy with ID " + policyDTO.getPolicyId() + " not found");
	}

	@Override
	public String deleteByPolicyId(long policyId) throws PolicyNotFoundException, PolicyRegisteredByUserException {
		logger.info("Deleting policy with ID: {}", policyId);

		Optional<UserPolicies> userPolicies = userPoliciesRepo.findById(policyId);
		if (!userPolicies.isEmpty()) {
			throw new PolicyRegisteredByUserException(
					"Policy with ID " + policyId + " is registered by user cannot be deleted");
		}

		Policies deletedPolicy = policiesRepo.findById(policyId)
				.orElseThrow(() -> new PolicyNotFoundException("Policy with ID " + policyId + " not found"));
		policiesRepo.deleteById(policyId);

		logger.info("Policy deleted successfully with ID: {}", policyId);
		return "Policy deleted succesfully";
	}

	@Override
	public List<Policies> getPolicyByPolicyType(String policyType) throws PolicyNotFoundException {

		logger.info("Fetching policies by policy type: {}", policyType);
		List<Policies> policies = policiesRepo.findByPolicyType(policyType);
		if (policies.isEmpty()) {
			logger.warn("No policies found for policy type: {}", policyType);
			throw new PolicyNotFoundException("No policies found for policy type: " + policyType);
		}
		logger.info("Policies retrieved by policy type: {}", policies);
		return policies;
	}

	@Override
	public List<Policies> getPolicyByCompany(String company) throws PolicyNotFoundException {
		logger.info("Fetching policies by company: {}", company);
		List<Policies> policies = policiesRepo.findByCompany(company);
		if (policies.isEmpty()) {
			logger.warn("No policies found for company: {}", company);
			throw new PolicyNotFoundException("No policies found for company: " + company);
		}
		logger.info("Policies retrieved by company: {}", policies);
		return policies;
	}

	@Override
	public List<Policies> getBytermAmountLessThan(double termAmount) throws PolicyNotFoundException {
		logger.info("Fetching policies with term amount less than: {}", termAmount);
		List<Policies> policies = policiesRepo.findBytermAmountLessThan(termAmount);
		if (policies.isEmpty()) {
			logger.warn("No policies found with term amount less than: {}", termAmount);
			throw new PolicyNotFoundException("No policies found with term amount less than: " + termAmount);
		}
		logger.info("Policies retrieved with term amount less than: {}", termAmount);
		return policies;
	}

	@Override
	public List<Policies> getBytermAmountGreaterThan(double termAmount) throws PolicyNotFoundException {
		logger.info("Fetching policies with term amount greater than: {}", termAmount);
		List<Policies> policies = policiesRepo.findBytermAmountGreaterThan(termAmount);
		if (policies.isEmpty()) {
			logger.warn("No policies found with term amount greater than: {}", termAmount);
			throw new PolicyNotFoundException("No policies found with term amount greater than: " + termAmount);
		}
		logger.info("Policies retrieved with term amount greater than: {}", termAmount);
		return policies;
	}

	@Override
	public List<Policies> getAllPolicies() {

		List<Policies> policies = policiesRepo.findAll();
		logger.info("retrived all policies succesfully" + policies);
		return policies;
	}

	@Override
	public PoliciesDTO getByPolicyId(long policyId) throws PolicyNotFoundException {
		logger.info("Fetching policy by ID: {}", policyId);
		Optional<Policies> optional = policiesRepo.findById(policyId);
		Policies policies = optional
				.orElseThrow(() -> new PolicyNotFoundException("Policy with ID " + policyId + " not found"));

		PoliciesDTO policiesDTO = new PoliciesDTO();
		policiesDTO.setPolicyId(policies.getPolicyId());
		policiesDTO.setPolicyName(policies.getPolicyName());
		policiesDTO.setPolicyDescription(policies.getPolicyDescription());
		policiesDTO.setCompany(policies.getCompany());
		policiesDTO.setPolicyType(policies.getPolicyType());
		policiesDTO.setInitialDeposit(policies.getInitialDeposit());
		policiesDTO.setTermPeriod(policies.getTermPeriod());
		policiesDTO.setTermAmount(policies.getTermAmount());
		policiesDTO.setInterest(policies.getInterest());
		policiesDTO.setEligibleUserTypes(policies.getEligibleUserTypes());

		logger.info("Policy retrieved successfully!");
		return policiesDTO;
	}
}