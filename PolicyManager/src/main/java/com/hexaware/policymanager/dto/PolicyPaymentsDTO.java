package com.hexaware.policymanager.dto;

import java.time.LocalDate;

import com.hexaware.policymanager.entities.UserPolicies;

public class PolicyPaymentsDTO {
	private long paymentId;
	private LocalDate paymentDate;
	private String paymentStatus;
	private double totalAmount;
	private double fine;
	private String paymentMethod;
	private UserPolicies userPolicies;
	private long userPolicyId;

	public PolicyPaymentsDTO() {
		super();
	}

	public PolicyPaymentsDTO(long paymentId, LocalDate paymentDate, String paymentStatus, double totalAmount,
			double fine, String paymentMethod, UserPolicies userPolicies) {
		super();
		this.paymentId = paymentId;
		this.paymentDate = paymentDate;
		this.paymentStatus = paymentStatus;
		this.totalAmount = totalAmount;
		this.fine = fine;
		this.paymentMethod = paymentMethod;
		this.userPolicies = userPolicies;
	}

	public long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(long paymentId) {
		this.paymentId = paymentId;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public double getFine() {
		return fine;
	}

	public void setFine(double fine) {
		this.fine = fine;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public UserPolicies getUserPolicies() {
		return userPolicies;
	}

	public void setUserPolicies(UserPolicies userPolicies) {
		this.userPolicies = userPolicies;
	}

	public long getUserPolicyId() {
		return userPolicyId;
	}

	public void setUserPolicyId(long userPolicyId) {
		this.userPolicyId = userPolicyId;
	}

	@Override
	public String toString() {
		return "PolicyPaymentsDTO [paymentId=" + paymentId + ", paymentDate=" + paymentDate + ", paymentStatus="
				+ paymentStatus + ", totalAmount=" + totalAmount + ", fine=" + fine + ", paymentMethod=" + paymentMethod
				+ ", userPolicies=" + userPolicies + ", userPolicyId=" + userPolicyId + "]";
	}

	

}