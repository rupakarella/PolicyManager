package com.hexaware.policymanager.dto;

import java.time.LocalDate;

import com.hexaware.policymanager.entities.UserPolicies;

public class PolicyPaymentsDTO {
	private long paymentId;
	private UserPolicies userPolicy;
	private long transactionId;
	private LocalDate paymentDate;
	private String bank;
	private Double amount;
	private Double fine;
	private String paymentStatus;
	public PolicyPaymentsDTO() {
		super();
	}
	public PolicyPaymentsDTO(long paymentId, UserPolicies userPolicy, long transactionId, LocalDate paymentDate,
			String bank, Double amount, Double fine, String paymentStatus) {
		super();
		this.paymentId = paymentId;
		this.userPolicy = userPolicy;
		this.transactionId = transactionId;
		this.paymentDate = paymentDate;
		this.bank = bank;
		this.amount = amount;
		this.fine = fine;
		this.paymentStatus = paymentStatus;
	}
	public long getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(long paymentId) {
		this.paymentId = paymentId;
	}
	public UserPolicies getUserPolicy() {
		return userPolicy;
	}
	public void setUserPolicy(UserPolicies userPolicy) {
		this.userPolicy = userPolicy;
	}
	public long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}
	public LocalDate getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Double getFine() {
		return fine;
	}
	public void setFine(Double fine) {
		this.fine = fine;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	@Override
	public String toString() {
		return "PolicyPaymentsDTO [paymentId=" + paymentId + ", userPolicy=" + userPolicy + ", transactionId="
				+ transactionId + ", paymentDate=" + paymentDate + ", bank=" + bank + ", amount=" + amount + ", fine="
				+ fine + ", paymentStatus=" + paymentStatus + "]";
	}
	

}
