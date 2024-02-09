package com.hexaware.policymanager.dto;

import java.util.Date;
import java.util.List;

import com.hexaware.policymanager.entities.Claims;
import com.hexaware.policymanager.entities.Policies;
import com.hexaware.policymanager.entities.Users;

public class UserPoliciesDTO {
	private long userPolicyId;
	private Date startDate;
	private long userId;
	private long policyId;
	private int durationInYears;
	private Users user;
	private Policies policy;
	private Date endDate;
	private List<Claims> claims;
	
	
	public UserPoliciesDTO() {
		super();
	}


	public UserPoliciesDTO(long userPolicyId, Date startDate, long userId, long policyId, int durationInYears,
			Users user, Policies policy, Date endDate, List<Claims> claims) {
		super();
		this.userPolicyId = userPolicyId;
		this.startDate = startDate;
		this.userId = userId;
		this.policyId = policyId;
		this.durationInYears = durationInYears;
		this.user = user;
		this.policy = policy;
		this.endDate = endDate;
		this.claims = claims;
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
	

	public List<Claims> getClaims() {
		return claims;
	}



	public void setClaims(List<Claims> claims) {
		this.claims= claims;
	}

	@Override
	public String toString() {
		return "UserPoliciesDTO [userPolicyId=" + userPolicyId + ", startDate=" + startDate + ", userId=" + userId
				+ ", policyId=" + policyId + ", durationInYears=" + durationInYears + ", user=" + user + ", policy="
				+ policy + ", endDate=" + endDate + ", claims=" + claims + "]";
	}



	
	

}