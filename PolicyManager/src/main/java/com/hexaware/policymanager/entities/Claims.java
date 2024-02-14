package com.hexaware.policymanager.entities;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "Claims")
public class Claims {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ClaimsSequenceGenerator")
	@SequenceGenerator(name = "ClaimsSequenceGenerator", sequenceName = "ClaimsSeq", allocationSize = 1,initialValue =130000)
	private int claimId;

	@NotNull(message = "ClaimDate should not be null")
	private LocalDate claimDate;

	@NotNull(message = "ClaimAmount should not be null")
	private double claimAmount;

	@NotBlank(message = "ClaimStatus should not be blank")
	@Pattern(regexp = "^(Pending|Approved|Rejected)$", message = "Status should be either Pending, Approved or Rejected")
	private String claimStatus;

	@ManyToOne
	@JoinColumn(name = "UserPolicyID")
	@JsonBackReference(value = "UserPolicies-Claims")
	private UserPolicies userPolicy;

	public Claims() {
		super();
	}

	public Claims(int claimId, @NotNull(message = "ClaimDate should not be null") LocalDate claimDate,
			@NotNull(message = "ClaimAmount should not be null") double claimAmount,
			@NotBlank(message = "ClaimStatus should not be blank") @Pattern(regexp = "^(Pending|Approved|Rejected)$", message = "Status should be either Pending, Approved or Rejected") String claimStatus,
			UserPolicies userPolicy) {
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

	@Override
	public String toString() {
		return "Claims [claimId=" + claimId + ", claimDate=" + claimDate + ", claimAmount=" + claimAmount
				+ ", claimStatus=" + claimStatus + ", userPolicy=" + userPolicy + "]";
	}

}
