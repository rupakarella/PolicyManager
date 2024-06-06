package com.hexaware.policymanager.dto;

import java.time.LocalDate;

import com.hexaware.policymanager.entities.UserPolicies;
import com.hexaware.policymanager.entities.Users;

public class PolicyPaymentsDTO {
	private long paymentId;
	private LocalDate paymentDate;
	private String paymentStatus;
	private double totalAmount;
	private double fine;
	private String paymentMethod;
	private UserPolicies userPolicies;
	private long userPolicyId;
	private Users users;
	private long userId;

	public PolicyPaymentsDTO() {
		super();
	}

	

	public PolicyPaymentsDTO(long paymentId, LocalDate paymentDate, String paymentStatus, double totalAmount,
			double fine, String paymentMethod, UserPolicies userPolicies, long userPolicyId, Users users, long userId) {
		super();
		this.paymentId = paymentId;
		this.paymentDate = paymentDate;
		this.paymentStatus = paymentStatus;
		this.totalAmount = totalAmount;
		this.fine = fine;
		this.paymentMethod = paymentMethod;
		this.userPolicies = userPolicies;
		this.userPolicyId = userPolicyId;
		this.users = users;
		this.userId = userId;
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
		return "PolicyPaymentsDTO [paymentId=" + paymentId + ", paymentDate=" + paymentDate + ", paymentStatus="
				+ paymentStatus + ", totalAmount=" + totalAmount + ", fine=" + fine + ", paymentMethod=" + paymentMethod
				+ ", userPolicies=" + userPolicies + ", userPolicyId=" + userPolicyId + ", users=" + users + ", userId="
				+ userId + "]";
	}



	
	

}