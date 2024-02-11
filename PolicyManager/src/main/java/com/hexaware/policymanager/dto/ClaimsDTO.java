package com.hexaware.policymanager.dto;

import java.util.Date;

import com.hexaware.policymanager.entities.UserPolicies;

public class ClaimsDTO {
	private int claimId;
	private Date claimDate;
	private double claimAmount;
	private String claimStatus;
	private UserPolicies userPolicy;
	private long userPolicyId;
	public ClaimsDTO() {
		super();
	}
	public ClaimsDTO(int claimId, Date claimDate, double claimAmount, String claimStatus, UserPolicies userPolicy) {
		super();
		this.claimId = claimId;
		this.claimDate = claimDate;
		this.claimAmount = claimAmount;
		this.claimStatus = claimStatus;
		this.userPolicy = userPolicy;
	}
	public int getClaimId() {
		return claimId;
	}
	public void setClaimId(int claimId) {
		this.claimId = claimId;
	}
	public Date getClaimDate() {
		return claimDate;
	}
	public void setClaimDate(Date claimDate) {
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
	@Override
	public String toString() {
		return "ClaimsDTO [claimId=" + claimId + ", claimDate=" + claimDate + ", claimAmount=" + claimAmount
				+ ", claimStatus=" + claimStatus + ", userPolicy=" + userPolicy + "]";
	}
	
	
	
}