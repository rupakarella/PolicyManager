package com.hexaware.policymanager.dto;

import java.util.Date;

import com.hexaware.policymanager.entities.Policies;
import com.hexaware.policymanager.entities.Users;

public class UserPoliciesDTO {
	private long userPolicyId;
	private Date startDate;
	private Date endDate;
	private long userId;
	private long policyId;
	private int durationInYears;
	private Users user;
	private Policies policy;
	
	
	public UserPoliciesDTO() {
		super();
	}


	public UserPoliciesDTO(long userPolicyId, Date startDate, Date endDate, long userId, long policyId,
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


	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	public long getUserId() {
		return userId;
	}


	public void setUserId(long userId) {
		this.userId = userId;
	}


	public long getPolicyId() {
		return policyId;
	}


	public void setPolicyId(long policyId) {
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
		return "UserPolicies [userPolicyId=" + userPolicyId + ", user=" + user + ", policy=" + policy
				+ ", startDate=" + startDate + ", endDate=" + endDate
				+ ", durationInYears=" + durationInYears + "]";
	}
	

}