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

import com.hexaware.policymanager.dto.PoliciesDTO;
import com.hexaware.policymanager.dto.UserPoliciesDTO;
import com.hexaware.policymanager.entities.UserPolicies;
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
		service.deleteUserPolicyById(policyId);
	}
	@GetMapping("/getall/{policyId}")
	public UserPoliciesDTO getById(@PathVariable long policyId)
	{
		return service.getbyUserPolicyId(policyId);
		
	}
	
	@GetMapping("/getall")
	public List<UserPolicies> getAllUserPolicies()
	{
		return service.getAllUserPolicies();
		
	}
	
	
			
}
