package com.hexaware.policymanager.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.hexaware.policymanager.service.IPolicyPaymentsService;

@RestController
@RequestMapping("/api/policy-payment")
public class PolicyPaymentsRestController {
	
	@Autowired
	IPolicyPaymentsService services;
	
	@PostMapping(value="/add")
	public PolicyPayments createPolicyPayment(@RequestBody PolicyPaymentsDTO policypaymentDTO)
	{
		return services.createPolicyPayment(policypaymentDTO);
	}
	
	@PutMapping("/update")
	public PolicyPayments updatePolicyPayment(@RequestBody PolicyPaymentsDTO policypaymentDTO)
	{
		return services.updatePolicyPayment(policypaymentDTO);
	}
	
	@DeleteMapping("/delete/{TransactionId}")
	public void deletePolicyByTransactionId(@PathVariable long TransactionId)
	{
		services.deletePolicyPaymentByTransactionnId(TransactionId);
	}
	
	@GetMapping("/get/transactionId/{TransactionId}")
	public PolicyPayments getPolicyPaymentByTrasactionId(@PathVariable long TransactionId)
	{
		return services.getPolicyPaymentBytransactionId(TransactionId);
	}
	
	@GetMapping("/getall")
	public List<PolicyPayments> getAllPolicyPayments()
	{
		return services.getAllPolicyPayments();
	}

}
