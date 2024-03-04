package com.hexaware.policymanager.service;

import java.time.LocalDate;
import java.util.List;

import com.hexaware.policymanager.dto.PolicyPaymentsDTO;
import com.hexaware.policymanager.entities.PolicyPayments;
import com.hexaware.policymanager.exception.PaymentNotFoundException;
import com.hexaware.policymanager.exception.UserNotFoundException;
import com.hexaware.policymanager.exception.UserPolicyNotFoundException;

public interface IPolicyPaymentsService {
	public PolicyPayments makePayment(PolicyPaymentsDTO policyPaymentsDTO) throws UserPolicyNotFoundException;

	public PolicyPayments updatePayment(PolicyPaymentsDTO policyPaymentsDTO) throws PaymentNotFoundException;

	public String deletePayment(long paymentId) throws PaymentNotFoundException;

	public PolicyPayments getByPaymentId(long paymentId) throws PaymentNotFoundException;

	public List<PolicyPayments> getAllPayments();
	
	List<PolicyPayments> getPaymentsByDate(LocalDate paymentDate) throws PaymentNotFoundException;

	public List<PolicyPayments> getPaymentsByPaymentStatus(String paymentStatus) throws PaymentNotFoundException;
	
	List<PolicyPayments> getPaymentsByUserId(long userId) throws UserNotFoundException;
	
	List<PolicyPayments> getPaymentsByUserPolicyId(long userId) throws UserPolicyNotFoundException;

}