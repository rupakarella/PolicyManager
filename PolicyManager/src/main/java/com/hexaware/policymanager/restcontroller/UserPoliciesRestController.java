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
import com.hexaware.policymanager.entities.Users;
import com.hexaware.policymanager.service.IUserPoliciesService;

@RestController
@RequestMapping("/api/userpolicies")
public class UserPoliciesRestController {
	@Autowired
	IUserPoliciesService service;
	@PostMapping(value = "/add")
	public UserPolicies createUserPolicy(@RequestBody UserPoliciesDTO userPoliciesDTO)
	{
		return service.createUserPolicy(userPoliciesDTO);
	}
	@PutMapping("/update")
	public UserPolicies updateUserPolicy(@RequestBody UserPoliciesDTO userPoliciesDTO)
	{
		return service.updateUserPolicy(userPoliciesDTO);
	}
	@DeleteMapping(value = "/delete/{policyId}")
	public void deleteUserPolicyByPolicyNo(@PathVariable long policyId)
	{
		service.deleteUserPolicyByPolicyId(policyId);
	}
	
	
	@GetMapping("/getall")
	public List<UserPolicies> getAllUserPolicies()
	{
		return service.getAllUserPolicies();
		
	}
	
	
	@GetMapping("/get/{policyNo}")
	public UserPolicies getUserPolicyByPolicyNo(@PathVariable long policyId)
	{
		return service.getUserPolicyByPolicyId(policyId);
		
	}
	
	@GetMapping("/get/userId/{userId}")
	public List<UserPolicies> getUserPolicyByUserId(@PathVariable long userId)
	{
		return service.getUserPolicyByUserId(userId);
		
	}
	
			
}
