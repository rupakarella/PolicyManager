package com.hexaware.policymanager.dto;

import java.time.LocalDate;
import java.util.List;

import com.hexaware.policymanager.entities.Claims;
import com.hexaware.policymanager.entities.Policies;
import com.hexaware.policymanager.entities.PolicyPayments;
import com.hexaware.policymanager.entities.Users;

public class UserPoliciesDTO {
	private long userPolicyId;
	private LocalDate startDate;
	private long userId;
	private long policyId;
	private int durationInYears;
	private Users user;
	private Policies policy;
	private LocalDate endDate;
	private List<Claims> claims;
	private List<PolicyPayments> policyPayments;
	private double maturityAmount;

	public UserPoliciesDTO() {
		super();
	}

	public UserPoliciesDTO(long userPolicyId, LocalDate startDate, long userId, long policyId, int durationInYears,
			Users user, Policies policy, LocalDate endDate, List<Claims> claims, List<PolicyPayments> policyPayments,
			double maturityAmount) {
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
		this.policyPayments = policyPayments;
		this.maturityAmount = maturityAmount;
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
		this.claims = claims;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public double getMaturityAmount() {
		return maturityAmount;
	}

	public void setMaturityAmount(double maturityAmount) {
		this.maturityAmount = maturityAmount;
	}

	public List<PolicyPayments> getPolicyPayments() {
		return policyPayments;
	}

	public void setPolicyPayments(List<PolicyPayments> policyPayments) {
		this.policyPayments = policyPayments;
	}

	@Override
	public String toString() {
		return "UserPoliciesDTO [userPolicyId=" + userPolicyId + ", startDate=" + startDate + ", userId=" + userId
				+ ", policyId=" + policyId + ", durationInYears=" + durationInYears + ", user=" + user + ", policy="
				+ policy + ", endDate=" + endDate + ", claims=" + claims + ", policyPayments=" + policyPayments
				+ ", maturityAmount=" + maturityAmount + "]";
	}

}