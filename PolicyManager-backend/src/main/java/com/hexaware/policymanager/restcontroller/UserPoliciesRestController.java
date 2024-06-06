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
 
import com.hexaware.policymanager.dto.UserPoliciesDTO;
import com.hexaware.policymanager.entities.UserPolicies;
import com.hexaware.policymanager.exception.PolicyNotFoundException;
import com.hexaware.policymanager.exception.UserNotFoundException;
import com.hexaware.policymanager.exception.UserPolicyNotFoundException;
import com.hexaware.policymanager.service.IUserPoliciesService;
 
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/userPolicies")
public class UserPoliciesRestController {
 
    @Autowired
    IUserPoliciesService userPolicyService;
 
    @PostMapping(value = "/register")
    @PreAuthorize("hasAnyAuthority('Admin','User')")
    public ResponseEntity<UserPolicies> createUserPolicy(@RequestBody UserPoliciesDTO userPoliciesDTO)
            throws UserNotFoundException, PolicyNotFoundException {
        UserPolicies userPolicy = userPolicyService.createUserPolicy(userPoliciesDTO);
        return new ResponseEntity<>(userPolicy, HttpStatus.CREATED);
    }
 
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<UserPolicies> updateUserPolicy(@RequestBody UserPoliciesDTO userPoliciesDTO)
            throws UserPolicyNotFoundException {
        UserPolicies userPolicy = userPolicyService.updateUserPolicy(userPoliciesDTO);
        return new ResponseEntity<>(userPolicy, HttpStatus.OK);
    }
 
    @DeleteMapping(value = "/delete/{userPolicyId}")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<Void> deleteUserPolicyByPolicyNo(@PathVariable long userPolicyId)
            throws UserPolicyNotFoundException {
        userPolicyService.deleteUserPolicyById(userPolicyId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
 
    @GetMapping("/get-userPolicyId/{userPolicyId}")
    @PreAuthorize("hasAnyAuthority('Admin','User')")
    public ResponseEntity<UserPolicies> getById(@PathVariable long userPolicyId)
            throws UserPolicyNotFoundException {
        UserPolicies userPolicy = userPolicyService.getbyUserPolicyId(userPolicyId);
        return new ResponseEntity<>(userPolicy, HttpStatus.OK);
    }
 
    @GetMapping("/get-all")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<List<UserPolicies>> getAllUserPolicies() {
        List<UserPolicies> userPoliciesList = userPolicyService.getAllUserPolicies();
        return new ResponseEntity<>(userPoliciesList, HttpStatus.OK);
    }
 
    @GetMapping("/get-by-userId/{userId}")
    @PreAuthorize("hasAnyAuthority('Admin','User')")
    public ResponseEntity<List<UserPolicies>> getUserPoliciesByUserId(@PathVariable long userId)
            throws UserNotFoundException {
        List<UserPolicies> userPoliciesList = userPolicyService.getUserPoliciesByUserId(userId);
        return new ResponseEntity<>(userPoliciesList, HttpStatus.OK);
    }
}