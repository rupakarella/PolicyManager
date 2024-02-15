package com.hexaware.policymanager.entities;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "UserPolicies")
public class UserPolicies {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UserPoliciesSequenceGenerator")
	@SequenceGenerator(name = "UserPoliciesSequenceGenerator", sequenceName = "UserPoliciesSeq", allocationSize = 1,initialValue =40000)
	private long userPolicyId;

	@ManyToOne
	@JoinColumn(name = "UserID")
	@JsonBackReference(value = "UserPolicies-Users")
	private Users user;

	@ManyToOne
	@JoinColumn(name = "PolicyID")
	private Policies policy;
	
	@OneToMany(mappedBy = "userPolicies", cascade = CascadeType.ALL)
	@JsonManagedReference(value = "UserPolicies-PolicyPayments")
    //@JsonIgnoreProperties("userPolicies")
	private List<PolicyPayments> policyPayments;

	@OneToMany(mappedBy = "userPolicy", cascade = CascadeType.ALL)
	@JsonManagedReference(value = "UserPolicies-Claims")
	private List<Claims> claims;

	@NotNull(message = "Start date cannot be null")
	@FutureOrPresent(message = "Start date must be in the present or future")
	private LocalDate startDate;

	private LocalDate endDate;
	private double maturityAmount;

	@NotNull(message = "duration cannot be null")
	@Positive(message = "duration should be a positive value")
	private int durationInYears;

	public UserPolicies() {
		super();
	}

	public UserPolicies(Users user, Policies policy, List<Claims> claims,List<PolicyPayments> policyPayments,
			@NotNull(message = "Start date cannot be null") @FutureOrPresent(message = "Start date must be in the present or future") LocalDate startDate,
			double maturityAmount,
			@NotNull(message = "duration cannot be null") @Positive(message = "duration should be a positive value") int durationInYears) {
		super();
		this.user = user;
		this.policy = policy;
		this.claims = claims;
		this.policyPayments=policyPayments;
		this.startDate = startDate;
		this.durationInYears = durationInYears;
		this.maturityAmount = maturityAmount;
		calculateEndDate();
		calculateMaturityAmount();
	}

	public long getUserPolicyId() {
		return userPolicyId;
	}

	public void setUserPolicyId(long userPolicyId) {
		this.userPolicyId = userPolicyId;
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

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public int getDurationInYears() {
		return durationInYears;
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

	public void setDurationInYears(int durationInYears) {
		this.durationInYears = durationInYears;
		calculateEndDate();
		calculateMaturityAmount();
	}

	public void calculateEndDate() {
		if (startDate != null) {
			this.endDate = startDate.plusYears(durationInYears);
		}
	}
	

	

	public void calculateMaturityAmount() {
		if (policy != null) {
			double initialDeposit = policy.getInitialDeposit();
			double termAmount = policy.getTermAmount();
			double interest = policy.getInterest();

			int termInYears = 0;
			switch (policy.getTermPeriod()) {
			case "Monthly":
				termInYears = 1;
				break;
			case "Quarterly":
				termInYears = 4;
				break;
			case "Half-Yearly":
				termInYears = 6;
				break;
			case "Annually":
				termInYears = 12;
				break;
			default:
				break;
			}

			this.maturityAmount = initialDeposit + (durationInYears * termInYears * termAmount)
					+ ((durationInYears * termInYears * termAmount) * (interest / 100));
		}
	}
}