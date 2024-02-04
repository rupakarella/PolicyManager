package com.hexaware.policymanager.dto;

import java.time.LocalDate;

import com.hexaware.policymanager.entities.Policies;
import com.hexaware.policymanager.entities.Users;

public class UserPoliciesDTO {
	private long userPolicyId;
	private LocalDate startDate;
	private LocalDate endDate;
	private Users userId;
	private Policies policyId;
	private int durationInYears;
	private Users user;
	private Policies policy;
	
	
	public UserPoliciesDTO() {
		super();
	}
	

	

	public UserPoliciesDTO(long userPolicyId, LocalDate startDate, LocalDate endDate, Users userId, Policies policyId,
			int durationInYears, Users user, Policies policy) {
		super();
		this.userPolicyId = userPolicyId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.userId = userId;
		this.policyId = policyId;
		this.durationInYears = durationInYears;
		this.user = user;
		this.policy = policy;
	}




	public long getUserPolicyId() {
		return userPolicyId;
	}
	public void setUserPolicyId(long userPolicyId) {
		this.userPolicyId = userPolicyId;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	public Users getUserId() {
		return userId;
	}

	public void setUserId(Users userId) {
		this.userId = userId;
	}

	public Policies getPolicyId() {
		return policyId;
	}

	public void setPolicyId(Policies policyId) {
		this.policyId = policyId;
	}

	

	public int getDurationInYears() {
		return durationInYears;
	}


	public void setDurationInYears(int durationInYears) {
		this.durationInYears = durationInYears;
	}


	public Users getUser() {
		return user;
	}




	public void setUser(Users user) {
		this.user = user;
	}




	public Policies getPolicy() {
		return policy;
	}




	public void setPolicy(Policies policy) {
		this.policy = policy;
	}




	@Override
	public String toString() {
		return "UserPoliciesDTO [userPolicyId=" + userPolicyId + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", userId=" + userId + ", policyId=" + policyId + ", durationInYears=" + durationInYears + ", user="
				+ user + ", policy=" + policy + "]";
	}




	


	
	
	
}
