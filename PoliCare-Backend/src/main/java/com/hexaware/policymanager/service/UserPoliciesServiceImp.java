package com.hexaware.policymanager.service;

<<<<<<< HEAD
=======
import java.io.IOException;
>>>>>>> rupa
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
=======
import org.springframework.mail.javamail.JavaMailSender;
>>>>>>> rupa
import org.springframework.stereotype.Service;

import com.hexaware.policymanager.dto.UserPoliciesDTO;
import com.hexaware.policymanager.entities.Policies;
import com.hexaware.policymanager.entities.UserPolicies;
import com.hexaware.policymanager.entities.Users;
import com.hexaware.policymanager.exception.PolicyNotFoundException;
import com.hexaware.policymanager.exception.UserNotFoundException;
import com.hexaware.policymanager.exception.UserPolicyNotFoundException;
import com.hexaware.policymanager.repository.PoliciesRepository;
import com.hexaware.policymanager.repository.UserPoliciesRepository;
import com.hexaware.policymanager.repository.UsersRepository;

<<<<<<< HEAD
=======
import jakarta.mail.MessagingException;
>>>>>>> rupa
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserPoliciesServiceImp implements IUserPoliciesService {

	Logger logger = LoggerFactory.getLogger(UserPoliciesServiceImp.class);

	@Autowired
	UserPoliciesRepository userPoliciesRepo;

	@Autowired
	UsersRepository usersRepo;

	@Autowired
	PoliciesRepository policiesRepo;
<<<<<<< HEAD
=======
	
	@Autowired
    private PdfGenerator pdfGenerator;

    @Autowired
    private EmailService emailService;
>>>>>>> rupa

	@Override
	public UserPolicies createUserPolicy(UserPoliciesDTO userpolicyDTO)
			throws UserNotFoundException, PolicyNotFoundException {
		logger.info("Creating user policy for User ID: {} and Policy ID: {}", userpolicyDTO.getUserId(),
				userpolicyDTO.getPolicyId());
		Optional<Users> optionalUser = usersRepo.findById(userpolicyDTO.getUserId());
		if (optionalUser.isEmpty()) {
			logger.error("User not found with ID: {}" ,userpolicyDTO.getUserId());
			throw new UserNotFoundException("User not found with ID: " + userpolicyDTO.getUserId());
		}
		Users user = optionalUser.get();
		Optional<Policies> optionalPolicy = policiesRepo.findById(userpolicyDTO.getPolicyId());
		if (optionalPolicy.isEmpty()) {
			logger.error("Policy not found with ID:{}", userpolicyDTO.getPolicyId());
			throw new PolicyNotFoundException("Policy not found with ID: " + userpolicyDTO.getPolicyId());
		}
		Policies policy = optionalPolicy.get();
		UserPolicies userpolicy = new UserPolicies();
		userpolicy.setStartDate(userpolicyDTO.getStartDate());
		userpolicy.setUser(user);
		userpolicy.setPolicy(policy);
		userpolicy.setDurationInYears(userpolicyDTO.getDurationInYears());
		logger.info("User policy created successfully");
<<<<<<< HEAD
		return userPoliciesRepo.save(userpolicy);
=======
		 // Generate PDF
        byte[] pdfBytes = null;
        try {
            pdfBytes = pdfGenerator.generatePdf(user.getEmailAddress(),
            		userpolicy.getStartDate(), userpolicy.getMaturityAmount(), userpolicy.getEndDate(),
            		userpolicy.getDurationInYears(),policy);
        } catch (IOException e) {
            logger.error("Error generating PDF: {}", e.getMessage());
            throw new RuntimeException("Error generating PDF", e);
        }

        // Send email with PDF attachment
        try {
            emailService.sendEmailWithAttachment(user.getEmailAddress(), pdfBytes);
        } catch (MessagingException e) {
            logger.error("Error sending email: {}", e.getMessage());
            throw new RuntimeException("Error sending email", e);
        }
		return userPoliciesRepo.save(userpolicy); 
		
>>>>>>> rupa
	}

	@Override
	public UserPolicies updateUserPolicy(UserPoliciesDTO userpolicyDTO) throws UserPolicyNotFoundException {

		logger.info("Updating user policy with ID: {}", userpolicyDTO.getUserPolicyId());
		Optional<UserPolicies> optionalUserPolicy = userPoliciesRepo.findById(userpolicyDTO.getUserPolicyId());
		if (optionalUserPolicy.isPresent()) {
			UserPolicies userpolicy = optionalUserPolicy.get();
			userpolicy.setStartDate(userpolicyDTO.getStartDate());
			userpolicy.setDurationInYears(userpolicyDTO.getDurationInYears());
			Optional<Policies> optionalPolicy = policiesRepo.findById(userpolicyDTO.getPolicyId());
			if (optionalPolicy.isEmpty()) {
				logger.error("Policy not found with ID:{}", userpolicyDTO.getPolicyId());
				throw new PolicyNotFoundException("Policy not found with ID: " + userpolicyDTO.getPolicyId());
			}
			Policies policy = optionalPolicy.get();
			userpolicy.setPolicy(policy);
			UserPolicies updatedUserPolicy = userPoliciesRepo.save(userpolicy);

			logger.info("User policy updated successfully");
			return updatedUserPolicy;
		} else {
			logger.error("User policy not found with ID: {}", userpolicyDTO.getUserPolicyId());
			throw new UserPolicyNotFoundException("User policy not found with ID: " + userpolicyDTO.getUserPolicyId());
		}
	}

	@Override
	public String deleteUserPolicyById(long userPolicyId) throws UserPolicyNotFoundException {
		logger.info("Deleting user policy with ID: {}", userPolicyId);
		if (userPoliciesRepo.existsById(userPolicyId)) {
			userPoliciesRepo.deleteById(userPolicyId);
			logger.info("User policy deleted successfully");
			return "Record deleted";
		} else {
			logger.error("User policy not found with ID: {}", userPolicyId);
			throw new UserPolicyNotFoundException("User policy not found with ID: " + userPolicyId);
		}
	}

	@Override
	public UserPolicies getbyUserPolicyId(long userPolicyId) throws UserPolicyNotFoundException {
		logger.info("Fetching user policy with ID: {}", userPolicyId);

		Optional<UserPolicies> optionalUserPolicy = userPoliciesRepo.findById(userPolicyId);
		if (optionalUserPolicy.isPresent()) {
			return optionalUserPolicy.get();
		} else {
			logger.error("User policy not found with ID: {}", userPolicyId);
			throw new UserPolicyNotFoundException("User policy not found with ID: " + userPolicyId);
		}
	}

	@Override
	public List<UserPolicies> getAllUserPolicies() {
		logger.info("Fetching all user policies");
		List<UserPolicies> userPolicies = userPoliciesRepo.findAll();
		logger.info("Fetched {} user policies", userPolicies.size());
		return userPolicies;
	}

	@Override
	public List<UserPolicies> getUserPoliciesByUserId(long userId) throws UserNotFoundException {
		logger.info("Fetching user policies for User ID: {}", userId);
		List<UserPolicies> userPolicies = userPoliciesRepo.getByUserUserId(userId);
		if (userPolicies.isEmpty()) {
			logger.error("User policies not found for User ID: {}", userId);
			throw new UserNotFoundException("User policies not found for User ID: " + userId);
		}
		logger.info("Fetched {} user policies for User ID: {}", userPolicies.size(), userId);
		return userPolicies;
	}
}