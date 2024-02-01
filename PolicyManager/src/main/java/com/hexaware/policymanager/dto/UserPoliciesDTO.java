package com.hexaware.policymanager.dto;

import java.sql.Date;

import com.hexaware.policymanager.entities.Policies;
import com.hexaware.policymanager.entities.PolicyPayments;
import com.hexaware.policymanager.entities.Users;

public class UserPoliciesDTO {
	private long userPolicyId;
	private Date startDate;
	private Date endDate;
	private Users userId;
	private Policies policyId;
	private PolicyPayments policyPayments;
	
	public UserPoliciesDTO() {
		super();
	}
	
	public UserPoliciesDTO(long userPolicyId, Date startDate, Date endDate, Users userId, Policies policyId,
			PolicyPayments policyPayments) {
		super();
		this.userPolicyId = userPolicyId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.userId = userId;
		this.policyId = policyId;
		this.policyPayments = policyPayments;
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

	public PolicyPayments getPolicyPayments() {
		return policyPayments;
	}

	public void setPolicyPayments(PolicyPayments policyPayments) {
		this.policyPayments = policyPayments;
	}

	@Override
	public String toString() {
		return "UserPoliciesDTO [userPolicyId=" + userPolicyId + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", userId=" + userId + ", policyId=" + policyId + ", policyPayments=" + policyPayments + "]";
	}

	
	
}
