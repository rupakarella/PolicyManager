package com.hexaware.policymanager.entities;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "Policy Payments")
public class PolicyPayments {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PolicyPaymentsSequenceGenerator")
	@SequenceGenerator(name = "PolicyPaymentsSequenceGenerator", sequenceName = "PolicyPaymentsSeq", allocationSize = 1, initialValue = 9000000)
	private long paymentId;

	@NotNull(message = "paymentDate cannot be null")
	@FutureOrPresent(message = "Start date must be in the present or future")
	private LocalDate paymentDate;

	@NotBlank(message = "paymentStatus should not be blank")
	@Pattern(regexp = "^(Pending|Completed)$", message = "paymentStatus should be either Pending or Completed")
	private String paymentStatus;


	private double totalAmount;
	private double fine;

	@NotBlank(message = "paymentMethod should not be blank")
	@Pattern(regexp = "^(Credit Card|Debit Card|Net Banking|Cash)$", message = "Invalid payment method")
	private String paymentMethod;

	@ManyToOne
	@JoinColumn(name = "UserPoliciesID")
	@JsonBackReference(value = "UserPolicies-PolicyPayments")
	//@JsonIgnoreProperties("policyPayments")
	private UserPolicies userPolicies;

	public PolicyPayments() {
		super();
	}

	public PolicyPayments(long paymentId,
			@NotNull(message = "paymentDate cannot be null") @FutureOrPresent(message = "Start date must be in the present or future") LocalDate paymentDate,
			@NotBlank(message = "paymentStatus should not be blank") @Pattern(regexp = "^(Pending|Completed)$", message = "paymentStatus should be either Pending or Completed") String paymentStatus,
			@Positive(message = "totalAmount should be positive") double totalAmount,
			@PositiveOrZero(message = "fine should be positive") double fine,
			@NotBlank(message = "paymentMethod should not be blank") @Pattern(regexp = "^(Credit Card|Debit Card|Net Banking|Cash)$", message = "Invalid payment method") String paymentMethod,
			UserPolicies userPolicies) {
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

	@Override
	public String toString() {
		return "PolicyPayments [paymentId=" + paymentId + ", paymentDate=" + paymentDate + ", paymentStatus="
				+ paymentStatus + ", totalAmount=" + totalAmount + ", fine=" + fine + ", paymentMethod=" + paymentMethod
				+ ", userPolicies=" + userPolicies + "]";
	}

	@PrePersist
	@PreUpdate
	public void calculateFine() {
		Policies policy = userPolicies.getPolicy();
		String termPeriod = policy.getTermPeriod();
		LocalDate startDate = userPolicies.getStartDate();
		LocalDate dueDate = null;
		if (termPeriod.equals("Monthly")) {
			dueDate = startDate.plusMonths(1);
		} else if (termPeriod.equals("Quarterly")) {
			dueDate = startDate.plusMonths(3);
		} else if (termPeriod.equals("Half-Yearly")) {
			dueDate = startDate.plusMonths(6);
		} else if (termPeriod.equals("Annually")) {
			dueDate = startDate.plusYears(1);
		}

		if (paymentDate.isAfter(dueDate)) {
			long daysPastDue = java.time.temporal.ChronoUnit.DAYS.between(dueDate, paymentDate);
			this.fine = daysPastDue * policy.getTermAmount() * 0.001;
		} else {
			this.fine = 0;
		}
		this.totalAmount = policy.getTermAmount() + this.fine;
	}

}