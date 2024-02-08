package com.hexaware.policymanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.policymanager.dto.UserPoliciesDTO;
import com.hexaware.policymanager.entities.Policies;
import com.hexaware.policymanager.entities.PolicyPayments;
import com.hexaware.policymanager.entities.UserPolicies;
import com.hexaware.policymanager.entities.Users;
import com.hexaware.policymanager.repository.PoliciesRepository;
import com.hexaware.policymanager.repository.PolicyPaymentsRepository;
import com.hexaware.policymanager.repository.UserPoliciesRepository;
import com.hexaware.policymanager.repository.UsersRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserPoliciesServiceImp implements IUserPoliciesService {

	@Autowired
	UserPoliciesRepository userPoliciesRepo;

	@Autowired
	UsersRepository userRepo;

	@Autowired
	PoliciesRepository policiesRepo;

	@Autowired
	PolicyPaymentsRepository paymentRepo;

	@Override
	public UserPolicies createUserPolicy(UserPoliciesDTO userpolicyDTO) {
		UserPolicies userpolicy = new UserPolicies();
		userpolicy.setStartDate(userpolicyDTO.getStartDate());
		userpolicy.setDurationInYears(userpolicyDTO.getDurationInYears());
		Users user =userpolicyDTO.getUser();
		userpolicy.setUser(user);
	    userRepo.save(user);
		Policies policy = userpolicyDTO.getPolicy();
		userpolicy.setPolicy(policy);
		policiesRepo.save(policy);
		List<PolicyPayments> payments = userpolicyDTO.getPolicyPayments();
		userpolicy.setPolicyPayments(payments);
		paymentRepo.saveAll(payments);
		return userPoliciesRepo.save(userpolicy);
	}

	@Override
	public UserPolicies updateUserPolicy(UserPoliciesDTO userpolicyDTO) {

		UserPolicies userpolicy = userPoliciesRepo.findById(userpolicyDTO.getUserPolicyId()).orElse(null);
		if (userpolicy != null) {
			userpolicy.setStartDate(userpolicyDTO.getStartDate());
			userpolicy.setDurationInYears(userpolicyDTO.getDurationInYears());
			return userPoliciesRepo.save(userpolicy);
		}
		return null;
	}

	@Override
	public String deleteUserPolicyById(long userPolicyId) {
		userPoliciesRepo.deleteById(userPolicyId);
		return " record deleted ";

	}

	@Override
	public List<UserPolicies> getAllUserPolicies() {
		return userPoliciesRepo.findAll();
	}

	@Override
	public UserPoliciesDTO getbyUserPolicyId(long userPolicyId) {
		Optional<UserPolicies> optional = userPoliciesRepo.findById(userPolicyId);
		if (optional.isPresent()) {
			UserPolicies userPolicies = optional.get();
			UserPoliciesDTO userPoliciesDTO = new UserPoliciesDTO();
			userPoliciesDTO.setUserPolicyId(userPolicies.getUserPolicyId());
			userPoliciesDTO.setStartDate(userPolicies.getStartDate());
			userPoliciesDTO.setDurationInYears(userPolicies.getDurationInYears());
			return userPoliciesDTO;
		}
		return null;
	}

}