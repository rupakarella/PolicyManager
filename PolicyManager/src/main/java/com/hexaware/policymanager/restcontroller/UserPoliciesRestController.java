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

import com.hexaware.policymanager.dto.UserPoliciesDTO;
import com.hexaware.policymanager.entities.UserPolicies;
import com.hexaware.policymanager.exception.PolicyNotFoundException;
import com.hexaware.policymanager.exception.UserNotFoundException;
import com.hexaware.policymanager.exception.UserPolicyNotFoundException;
import com.hexaware.policymanager.service.IUserPoliciesService;

@RestController
@RequestMapping("/api/userPolicies")
public class UserPoliciesRestController {

	@Autowired
	IUserPoliciesService service;

	@PostMapping(value = "/add")
	public UserPolicies createUserPolicy(@RequestBody UserPoliciesDTO userPoliciesDTO)throws UserNotFoundException,PolicyNotFoundException  {
		return service.createUserPolicy(userPoliciesDTO);
	}

	@PutMapping("/update")
	public UserPolicies updateUserPolicy(@RequestBody UserPoliciesDTO userPoliciesDTO)throws UserPolicyNotFoundException {
		return service.updateUserPolicy(userPoliciesDTO);
	}

	@DeleteMapping(value = "/delete/{userPolicyId}")
	public String deleteUserPolicyByPolicyNo(@PathVariable long userPolicyId)throws UserPolicyNotFoundException {
		return service.deleteUserPolicyById(userPolicyId);
	}

	@GetMapping("/get-userPolicyId/{userPolicyId}")
	public UserPoliciesDTO getById(@PathVariable long userPolicyId) throws UserPolicyNotFoundException{
		return service.getbyUserPolicyId(userPolicyId);

	}

	@GetMapping("/getAll")
	public List<UserPolicies> getAllUserPolicies() {
		return service.getAllUserPolicies();

	}
	
	@GetMapping("/getByUserId/{userId}")
    public List<UserPolicies> getUserPoliciesByUserId(@PathVariable long userId)throws UserNotFoundException {
        return service.getUserPoliciesByUserId(userId);
    }

}