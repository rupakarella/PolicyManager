package com.hexaware.policymanager.restcontroller;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.bind.annotation.RestController;
 
import com.hexaware.policymanager.dto.PoliciesDTO;
import com.hexaware.policymanager.entities.Policies;
import com.hexaware.policymanager.exception.PolicyNotFoundException;
import com.hexaware.policymanager.exception.PolicyRegisteredByUserException;
import com.hexaware.policymanager.service.IPoliciesService;
 
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/policies")
public class PoliciesRestController {
 
	@Autowired
	IPoliciesService policiesService;
 
//	@PostMapping(value = "/register")
//	@PreAuthorize("hasAuthority('Admin')")
//	public Policies addPolicy(@RequestBody PoliciesDTO policyDTO) {
//		return policiesService.createPolicy(policyDTO);
//	}
 
	@PostMapping(value = "/register")
	@PreAuthorize("hasAuthority('Admin')")
	public ResponseEntity<Policies> addPolicy(@RequestBody PoliciesDTO policyDTO) {
		Policies policy = policiesService.createPolicy(policyDTO);
		return new ResponseEntity<>(policy, HttpStatus.CREATED);
	}
 
//	@PutMapping(value = "/update")
//	@PreAuthorize("hasAuthority('Admin')")
//	public Policies updatePolicy(@RequestBody PoliciesDTO policyDTO) throws PolicyNotFoundException {
//		return policiesService.updatePolicy(policyDTO);
//	}
 
	@PutMapping(value = "/update")
	@PreAuthorize("hasAuthority('Admin')")
	public ResponseEntity<Policies> updatePolicy(@RequestBody PoliciesDTO policyDTO) throws PolicyNotFoundException {
		Policies policy = policiesService.updatePolicy(policyDTO);
		return new ResponseEntity<>(policy, HttpStatus.OK);
	}
 
//	@DeleteMapping(value = "/delete/{policyId}")
//	@PreAuthorize("hasAuthority('Admin')")
//	public String deletePolicy(@PathVariable Long policyId)
//			throws PolicyNotFoundException, PolicyRegisteredByUserException {
//		policiesService.deleteByPolicyId(policyId);
//		return "PolicyDeleted";
//	}
 
	@DeleteMapping(value = "/delete/{policyId}")
	@PreAuthorize("hasAuthority('Admin')")
	public ResponseEntity<String> deletePolicy(@PathVariable Long policyId)
			throws PolicyNotFoundException, PolicyRegisteredByUserException {
		policiesService.deleteByPolicyId(policyId);
		return new ResponseEntity<>("PolicyDeleted", HttpStatus.NO_CONTENT);
	}
 
//	@GetMapping(value = "/get-policyType/{policyType}")
//	public List<Policies> getPoliciesByPolicyType(@PathVariable String policyType) throws PolicyNotFoundException {
//		return policiesService.getPolicyByPolicyType(policyType);
//	}
 
	@GetMapping(value = "/get-policyType/{policyType}")
	public ResponseEntity<List<Policies>> getPoliciesByPolicyType(@PathVariable String policyType)
			throws PolicyNotFoundException {
		List<Policies> policies = policiesService.getPolicyByPolicyType(policyType);
		return new ResponseEntity<>(policies, HttpStatus.OK);
	}
 
//	@GetMapping(value = "/get-company/{company}")
//	@PreAuthorize("hasAnyAuthority('Admin','User')")
//	public List<Policies> getPoliciesByCompany(@PathVariable String company) throws PolicyNotFoundException {
//		return policiesService.getPolicyByCompany(company);
//	}
 
	@GetMapping(value = "/get-company/{company}")
	@PreAuthorize("hasAnyAuthority('Admin','User')")
	public ResponseEntity<List<Policies>> getPoliciesByCompany(@PathVariable String company)
			throws PolicyNotFoundException {
		List<Policies> policies = policiesService.getPolicyByCompany(company);
		if (policies.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Policies>>(policies, HttpStatus.OK);
	}
 
	@GetMapping(value = "/get-term-amount-lower/{termAmount}")
	@PreAuthorize("hasAnyAuthority('Admin','User')")
	public ResponseEntity<List<Policies>> getByAmountLessThan(@PathVariable long termAmount)
			throws PolicyNotFoundException {
		List<Policies> policies = policiesService.getBytermAmountLessThan(termAmount);
		if (policies.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Policies>>(policies, HttpStatus.OK);
	}
 
	@GetMapping(value = "/get-term-amount-greater/{termAmount}")
	@PreAuthorize("hasAnyAuthority('Admin','User')")
	public ResponseEntity<List<Policies>> getByAmountGreaterThan(@PathVariable long termAmount)
			throws PolicyNotFoundException {
		List<Policies> policies = policiesService.getBytermAmountGreaterThan(termAmount);
		if (policies.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Policies>>(policies, HttpStatus.OK);
	}
 
	@GetMapping("/get-all")
	public ResponseEntity<List<Policies>> getAllPolicies() {
		List<Policies> policies = policiesService.getAllPolicies();
		if (policies.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(policies, HttpStatus.OK);
	}
 
}