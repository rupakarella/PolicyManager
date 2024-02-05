package com.hexaware.policymanager.entities;

import java.time.LocalDate;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "PolicyPayments")
public class PolicyPayments {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PaymentID")
	private long paymentId;

	@ManyToOne
	@JoinColumn(name = "UserPolicyID")
	private UserPolicies userPolicy;

	@Column(name = "TransactionID")
	private long transactionId;

	@NotNull
	@Column(name = "PaymentDate")
	private LocalDate paymentDate;

	@NotEmpty
	@Column(name = "Bank")
	private String bank;

	@Column(name = "Amount")
	private Double amount;

	@Column(name = "Fine")
	private Double fine;

	@Pattern(regexp ="^(Pending|Completed)$")
	private String paymentStatus;

	public PolicyPayments() {
		super();
	}

	public PolicyPayments(long paymentId, UserPolicies userPolicy, long transactionId, LocalDate paymentDate, String bank,
			Double amount, Double fine, String paymentStatus) {
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

}
