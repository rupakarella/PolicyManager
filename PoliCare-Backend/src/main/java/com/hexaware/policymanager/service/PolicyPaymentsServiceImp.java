package com.hexaware.policymanager.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.policymanager.dto.PolicyPaymentsDTO;
import com.hexaware.policymanager.entities.PolicyPayments;
import com.hexaware.policymanager.entities.UserPolicies;
import com.hexaware.policymanager.entities.Users;
import com.hexaware.policymanager.exception.PaymentNotFoundException;
import com.hexaware.policymanager.exception.UserNotFoundException;
import com.hexaware.policymanager.exception.UserPolicyNotFoundException;
import com.hexaware.policymanager.repository.PolicyPaymentsRepository;
import com.hexaware.policymanager.repository.UserPoliciesRepository;
import com.hexaware.policymanager.repository.UsersRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PolicyPaymentsServiceImp implements IPolicyPaymentsService {
	Logger logger = LoggerFactory.getLogger(PolicyPaymentsServiceImp.class);

	@Autowired
	PolicyPaymentsRepository policyPaymentsRepo;

	@Autowired
	UserPoliciesRepository userPoliciesRepo;

	@Autowired
	UsersRepository usersRepo;

	@Override
	public PolicyPayments makePayment(PolicyPaymentsDTO policyPaymentsDTO) throws UserPolicyNotFoundException {
		logger.info("making payment");
		PolicyPayments policyPayments = new PolicyPayments();
		policyPayments.setPaymentDate(policyPaymentsDTO.getPaymentDate());
		policyPayments.setPaymentMethod(policyPaymentsDTO.getPaymentMethod());
		policyPayments.setFine(policyPaymentsDTO.getFine());
		policyPayments.setTotalAmount(policyPaymentsDTO.getTotalAmount());
		policyPayments.setPaymentStatus(policyPaymentsDTO.getPaymentStatus());
		Optional<Users> optionalUser = usersRepo.findById(policyPaymentsDTO.getUserId());
		Optional<UserPolicies> optionalUserPolicy = userPoliciesRepo.findById(policyPaymentsDTO.getUserPolicyId());

		if (optionalUserPolicy.isPresent() && optionalUser.isPresent()) {
			Users users = optionalUser.get();
			policyPayments.setUsers(users);
			UserPolicies userPolicy = optionalUserPolicy.get();
			policyPayments.setUserPolicies(userPolicy);
			PolicyPayments savedPayment = policyPaymentsRepo.save(policyPayments);
			logger.info("Payment done successfully {}", savedPayment);
			return savedPayment;
		} else {
			logger.warn("User policy not found with ID: {}", policyPaymentsDTO.getUserPolicyId());
			throw new UserPolicyNotFoundException(
					"UserPolicy not found with ID: " + policyPaymentsDTO.getUserPolicyId());
		}
	}

	@Override
	public PolicyPayments updatePayment(PolicyPaymentsDTO policyPaymentsDTO) throws PaymentNotFoundException {
		logger.info("updating payment");
		Optional<PolicyPayments> optionalPayments = policyPaymentsRepo.findById(policyPaymentsDTO.getPaymentId());
		if (optionalPayments.isPresent()) {
			PolicyPayments policyPayments = optionalPayments.get();
			policyPayments.setPaymentId(policyPaymentsDTO.getPaymentId());
			policyPayments.setPaymentDate(policyPaymentsDTO.getPaymentDate());
			policyPayments.setPaymentMethod(policyPaymentsDTO.getPaymentMethod());
			policyPayments.setFine(policyPaymentsDTO.getFine());
			policyPayments.setTotalAmount(policyPaymentsDTO.getTotalAmount());
			policyPayments.setPaymentStatus(policyPaymentsDTO.getPaymentStatus());
			PolicyPayments updatedPayment = policyPaymentsRepo.save(policyPayments);
			logger.info("Payment updated successfully{}", updatedPayment);
			return updatedPayment;
		} else {
			logger.warn("Payment not found with ID {}", policyPaymentsDTO.getPaymentId());
			throw new PaymentNotFoundException("Payment with ID" + policyPaymentsDTO.getPaymentId() + " not found");
		}
	}

	@Override
	public String deletePayment(long paymentId) throws PaymentNotFoundException {
		logger.info("deleting payment");
		Optional<PolicyPayments> optionalPayments = policyPaymentsRepo.findById(paymentId);
		if (optionalPayments.isPresent()) {
			policyPaymentsRepo.deleteById(paymentId);
			return "Payment deleted";
		} else {
			logger.warn("Payment not found with ID {}", paymentId);
			throw new PaymentNotFoundException("Payment with ID " + paymentId + " not found");
		}

	}

	@Override
	public PolicyPayments getByPaymentId(long paymentId) throws PaymentNotFoundException {
		logger.info("Fetching payment with ID: {}", paymentId);
		Optional<PolicyPayments> optionalPayments = policyPaymentsRepo.findById(paymentId);
		if (optionalPayments.isPresent()) {
			logger.info("Payment found");
			return optionalPayments.get();
		} else {
			logger.warn("Payment not found");
			throw new PaymentNotFoundException("Payment with ID " + paymentId + " not found");
		}
	}

	@Override
	public List<PolicyPayments> getAllPayments() {
		logger.info("Fetching all payments");
		return policyPaymentsRepo.findAll();
	}

	@Override
	public List<PolicyPayments> getPaymentsByPaymentStatus(String paymentStatus) throws PaymentNotFoundException {
		logger.info("Fetching payment with ID: {}", paymentStatus);
		List<PolicyPayments> payments = policyPaymentsRepo.getPaymentsByPaymentStatus(paymentStatus);
		if (payments.isEmpty()) {
			logger.warn("Payment not found");
			throw new PaymentNotFoundException("Payment with ID " + paymentStatus + " not found");
		}
		return payments;
	}

	@Override
	public List<PolicyPayments> getPaymentsByDate(LocalDate paymentDate) throws PaymentNotFoundException {
		logger.info("Fetching payments by date: {}", paymentDate);
		List<PolicyPayments> payments = policyPaymentsRepo.getPaymentsByPaymentDate(paymentDate);
		if (payments.isEmpty()) {
			logger.warn("No payments found for date: {}", paymentDate);
			throw new PaymentNotFoundException("No payments found for date: " + paymentDate);
		}
		return payments;
	}

	@Override
	public List<PolicyPayments> getPaymentsByUserPolicyId(long userPolicyId) throws UserPolicyNotFoundException {
		logger.info("Fetching payments by userPolicyId: {}", userPolicyId);
		List<PolicyPayments> payments = policyPaymentsRepo.findByUserPolicies_UserPolicyId(userPolicyId);
		if (payments.isEmpty()) {
			logger.warn("No payments found for userPolicyId: {} ", userPolicyId);
			throw new UserPolicyNotFoundException("No payments found for userPolicyId: " + userPolicyId);
		}
		return payments;
	}

	@Override
	public List<PolicyPayments> getPaymentsByUserId(long userId) throws UserNotFoundException {
		logger.info("Fetching payments by userId: {}", userId);
		List<PolicyPayments> payments = policyPaymentsRepo.findByUsers_UserId(userId);
		if (payments.isEmpty()) {
			logger.warn("No payments found for userId: {} ", userId);
			throw new UserNotFoundException("No payments found for userId: " + userId);
		}
		return payments;
	}

}