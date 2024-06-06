package com.hexaware.policymanager.restcontroller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.policymanager.dto.PolicyPaymentsDTO;
import com.hexaware.policymanager.entities.PolicyPayments;
import com.hexaware.policymanager.exception.PaymentNotFoundException;
import com.hexaware.policymanager.exception.UserNotFoundException;
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
	public ResponseEntity<PolicyPayments> makePayments(@RequestBody PolicyPaymentsDTO policyPaymentsDTO)
			throws UserPolicyNotFoundException {
		PolicyPayments payment = paymentsService.makePayment(policyPaymentsDTO);
		return ResponseEntity.ok(payment);
	}

	@PutMapping("/update")
	@PreAuthorize("hasAuthority('Admin')")
	public ResponseEntity<PolicyPayments> updatePayment(@RequestBody PolicyPaymentsDTO policyPaymentsDTO)
			throws PaymentNotFoundException {
		try {
			PolicyPayments updatedPayment = paymentsService.updatePayment(policyPaymentsDTO);
			return ResponseEntity.ok(updatedPayment);
		} catch (PaymentNotFoundException e) {
			throw new PaymentNotFoundException("Payment not found: " + e.getMessage());
		}
	}

	@DeleteMapping("/delete/{paymentId}")
	@PreAuthorize("hasAuthority('Admin')")
	public ResponseEntity<String> deletePaymentsById(@PathVariable int paymentId) throws PaymentNotFoundException {
		String result = paymentsService.deletePayment(paymentId);
		return ResponseEntity.ok(result);
	}

	@GetMapping("/get-by-id/{paymentId}")
	@PreAuthorize("hasAnyAuthority('Admin','User')")
	public ResponseEntity<PolicyPayments> getByPaymentId(@PathVariable int paymentId) throws PaymentNotFoundException {
		PolicyPayments payment = paymentsService.getByPaymentId(paymentId);
		return ResponseEntity.ok(payment);
	}

	@GetMapping("/get-all")
	@PreAuthorize("hasAuthority('Admin')")
	public ResponseEntity<List<PolicyPayments>> getAllPayments() {
		List<PolicyPayments> payments = paymentsService.getAllPayments();
		return ResponseEntity.ok(payments);
	}

	@GetMapping("/get-by-payment-Date/{paymentDate}")
	@PreAuthorize("hasAnyAuthority('Admin','User')")
	public List<PolicyPayments> getPaymentsByPaymentDate(@PathVariable LocalDate paymentDate)
			throws PaymentNotFoundException {
		return paymentsService.getPaymentsByDate(paymentDate);
	}

	@GetMapping("/get-by-payment-status/{paymentStatus}")
	@PreAuthorize("hasAuthority('Admin')")
	public ResponseEntity<List<PolicyPayments>> getPaymentsByPaymentStatus(@PathVariable String paymentStatus)
			throws PaymentNotFoundException {
		List<PolicyPayments> payments = paymentsService.getPaymentsByPaymentStatus(paymentStatus);
		return ResponseEntity.ok(payments);
	}

	@GetMapping("/get-by-userPolicyId/{userPolicyId}")
	@PreAuthorize("hasAuthority('User')")
	public ResponseEntity<List<PolicyPayments>> getPaymentsByUserPolicyId(@PathVariable long userPolicyId)
			throws UserPolicyNotFoundException {
		List<PolicyPayments> payments = paymentsService.getPaymentsByUserPolicyId(userPolicyId);
		return ResponseEntity.ok(payments);
	}

	@GetMapping("/get-by-userId/{userId}")
	@PreAuthorize("hasAuthority('User')")
	public ResponseEntity<List<PolicyPayments>> getPaymentsByUserId(@PathVariable long userId)
			throws UserNotFoundException {
		List<PolicyPayments> payments = paymentsService.getPaymentsByUserId(userId);
		return ResponseEntity.ok(payments);
	}
}