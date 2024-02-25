package com.hexaware.policymanager.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.policymanager.dto.PolicyPaymentsDTO;
import com.hexaware.policymanager.entities.PolicyPayments;
import com.hexaware.policymanager.exception.PaymentNotFoundException;
import com.hexaware.policymanager.exception.UserPolicyNotFoundException;
import com.hexaware.policymanager.service.IPolicyPaymentsService;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/payments")
public class PaymentRestController {
	@Autowired
	IPolicyPaymentsService paymentsService;

	@PostMapping("/register")
	@PreAuthorize("hasAnyAuthority('User','Admin')")
	public PolicyPayments makePayments(@RequestBody PolicyPaymentsDTO policyPaymentsDTO)
			throws UserPolicyNotFoundException {
		return paymentsService.makePayment(policyPaymentsDTO);
	}

	@PutMapping("/update")
	@PreAuthorize("hasAuthority('Admin')")
	public PolicyPayments updatePayment(@RequestBody PolicyPaymentsDTO policyPaymentsDTO)
			throws PaymentNotFoundException {
		try {
			return paymentsService.updatePayment(policyPaymentsDTO);
		} catch (PaymentNotFoundException e) {
			throw new PaymentNotFoundException("Payment not found: " + e.getMessage());
		}
	}

	@DeleteMapping("/delete/{paymentId}")
	@PreAuthorize("hasAuthority('Admin')")
	public String deletePaymentsById(@PathVariable int paymentId) throws PaymentNotFoundException {
		return paymentsService.deletePayment(paymentId);
	}

	@GetMapping("/get-by-id/{paymentId}")
	@PreAuthorize("hasAnyAuthority('Admin','User')")
	public PolicyPayments getByPaymentId(@PathVariable int paymentId) throws PaymentNotFoundException {
		return paymentsService.getByPaymentId(paymentId);
	}

	@GetMapping("/get-all")
	@PreAuthorize("hasAuthority('Admin')")
	public List<PolicyPayments> getAllPayments() {
		return paymentsService.getAllPayments();
	}

	@GetMapping("/get-by-payment-status/{paymentStatus}")
	@PreAuthorize("hasAuthority('Admin')")
	public List<PolicyPayments> getPaymentsByPaymentStatus(@PathVariable String paymentStatus)
			throws PaymentNotFoundException {
		return paymentsService.getPaymentsByPaymentStatus(paymentStatus);
	}
}
