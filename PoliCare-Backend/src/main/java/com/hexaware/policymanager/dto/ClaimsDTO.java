package com.hexaware.policymanager.dto;

import java.time.LocalDate;

import com.hexaware.policymanager.entities.UserPolicies;
import com.hexaware.policymanager.entities.Users;

public class ClaimsDTO {
	private int claimId;
	private LocalDate claimDate;
	private double claimAmount;
	private String claimStatus;
	private UserPolicies userPolicy;
	private long userPolicyId;
	private Users users;
	private long userId;

	public ClaimsDTO() {
		super();
	}

	public ClaimsDTO(int claimId, LocalDate claimDate, double claimAmount, String claimStatus, UserPolicies userPolicy,
			long userPolicyId, Users users, long userId) {
		super();
		this.claimId = claimId;
		this.claimDate = claimDate;
		this.claimAmount = claimAmount;
		this.claimStatus = claimStatus;
		this.userPolicy = userPolicy;
		this.userPolicyId = userPolicyId;
		this.users = users;
		this.userId = userId;
	}

	public int getClaimId() {
		return claimId;
	}

	public void setClaimId(int claimId) {
		this.claimId = claimId;
	}

	public LocalDate getClaimDate() {
		return claimDate;
	}

	public void setClaimDate(LocalDate claimDate) {
		this.claimDate = claimDate;
	}

	public double getClaimAmount() {
		return claimAmount;
	}

	public void setClaimAmount(double claimAmount) {
		this.claimAmount = claimAmount;
	}

	public String getClaimStatus() {
		return claimStatus;
	}

	public void setClaimStatus(String claimStatus) {
		this.claimStatus = claimStatus;
	}

	public UserPolicies getUserPolicy() {
		return userPolicy;
	}

	public void setUserPolicy(UserPolicies userPolicy) {
		this.userPolicy = userPolicy;
	}

	public long getUserPolicyId() {

		return userPolicyId;
	}

	public void setUserPolicyId(long userPolicyId) {
		this.userPolicyId = userPolicyId;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "ClaimsDTO [claimId=" + claimId + ", claimDate=" + claimDate + ", claimAmount=" + claimAmount
				+ ", claimStatus=" + claimStatus + ", userPolicy=" + userPolicy + ", userPolicyId=" + userPolicyId
				+ ", users=" + users + ", userId=" + userId + "]";
	}

}