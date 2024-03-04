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

import com.hexaware.policymanager.dto.UserPoliciesDTO;
import com.hexaware.policymanager.entities.UserPolicies;
import com.hexaware.policymanager.exception.PolicyNotFoundException;
import com.hexaware.policymanager.exception.UserNotFoundException;
import com.hexaware.policymanager.exception.UserPolicyNotFoundException;
import com.hexaware.policymanager.service.IUserPoliciesService;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/v1/userPolicies")
public class UserPoliciesRestController {

	@Autowired
	IUserPoliciesService userPolicyService;

	@PostMapping(value = "/register")
	@PreAuthorize("hasAnyAuthority('Admin','User')")
	public UserPolicies createUserPolicy(@RequestBody UserPoliciesDTO userPoliciesDTO)throws UserNotFoundException,PolicyNotFoundException  {
		return userPolicyService.createUserPolicy(userPoliciesDTO);
	}

	@PutMapping("/update")
	@PreAuthorize("hasAuthority('Admin')")
	public UserPolicies updateUserPolicy(@RequestBody UserPoliciesDTO userPoliciesDTO)throws UserPolicyNotFoundException {
		return userPolicyService.updateUserPolicy(userPoliciesDTO);
	}

	@DeleteMapping(value = "/delete/{userPolicyId}")
	@PreAuthorize("hasAuthority('Admin')")
	public String deleteUserPolicyByPolicyNo(@PathVariable long userPolicyId)throws UserPolicyNotFoundException {
		return userPolicyService.deleteUserPolicyById(userPolicyId);
	}

	@GetMapping("/get-userPolicyId/{userPolicyId}")
	@PreAuthorize("hasAnyAuthority('Admin','User')")
	public UserPolicies getById(@PathVariable long userPolicyId) throws UserPolicyNotFoundException{
		return userPolicyService.getbyUserPolicyId(userPolicyId);

	}

	@GetMapping("/get-all")
	@PreAuthorize("hasAuthority('Admin')")
	public List<UserPolicies> getAllUserPolicies() {
		return userPolicyService.getAllUserPolicies();

	}
	
	@GetMapping("/get-by-userId/{userId}")
	@PreAuthorize("hasAnyAuthority('Admin','User')")
    public List<UserPolicies> getUserPoliciesByUserId(@PathVariable long userId)throws UserNotFoundException {
        return userPolicyService.getUserPoliciesByUserId(userId);
    }

}