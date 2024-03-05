package com.hexaware.policymanager.entities;

import java.time.LocalDate;

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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "PolicyPayments")
public class PolicyPayments {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PolicyPaymentsSequenceGenerator")
	@SequenceGenerator(name = "PolicyPaymentsGenerator", sequenceName = "PolicyPaymentsSeq", allocationSize = 1, initialValue = 9000000)
	private long paymentId;

	@NotNull(message = "paymentDate cannot be null")
	private LocalDate paymentDate;

	@NotBlank(message = "paymentStatus should not be blank")
	@Pattern(regexp = "^(Pending|Completed)$", message = "paymentStatus should be either Pending or Completed")
	private String paymentStatus;

	private double totalAmount;
	private double fine;

	@NotBlank(message = "paymentMethod should not be blank")
	@Pattern(regexp = "^(Credit Card|Debit Card|Net Banking|Cash)$", message = "Invalid payment method")
	private String paymentMethod;

	@ManyToOne
	@JoinColumn(name = "UserPolicyID")
	private UserPolicies userPolicies;

	@ManyToOne
	@JoinColumn(name = "UserID")
	private Users users;

	public PolicyPayments() {
		super();
	}

	public PolicyPayments(long paymentId, @NotNull(message = "paymentDate cannot be null") LocalDate paymentDate,
			@NotBlank(message = "paymentStatus should not be blank") @Pattern(regexp = "^(Pending|Completed)$", message = "paymentStatus should be either Pending or Completed") String paymentStatus,
			double totalAmount, double fine,
			@NotBlank(message = "paymentMethod should not be blank") @Pattern(regexp = "^(Credit Card|Debit Card|Net Banking|Cash)$", message = "Invalid payment method") String paymentMethod,
			UserPolicies userPolicies, Users users) {
		super();
		this.paymentId = paymentId;
		this.paymentDate = paymentDate;
		this.paymentStatus = paymentStatus;
		this.totalAmount = totalAmount;
		this.fine = fine;
		this.paymentMethod = paymentMethod;
		this.userPolicies = userPolicies;
		this.users = users;
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

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "PolicyPayments [paymentId=" + paymentId + ", paymentDate=" + paymentDate + ", paymentStatus="
				+ paymentStatus + ", totalAmount=" + totalAmount + ", fine=" + fine + ", paymentMethod=" + paymentMethod
				+ ", userPolicies=" + userPolicies + ", users=" + users + "]";
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
