package com.hexaware.policymanager.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "Policies")
public class Policies {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long policyId;

	@NotBlank(message = "policyName should not be blank")
	private String policyName;

	@NotBlank(message = "policyDescription should not be blank")
	private String policyDescription;

	@NotBlank(message = "company should not be blank")
	private String company;

	@NotBlank(message = "policyType should not be blank")
	private String policyType;

	@Positive(message = "initialDeposit should be positive value")
	private double initialDeposit;

	@NotBlank(message = "termPeriod should not be blank")
	@Pattern(regexp = "^(Monthly|Quaterly|Half-Yearly|Annually)$")
	private String termPeriod;

	@Positive(message = "termAmount should be positive value")
	private double termAmount;

	@NotNull(message = "interest should not be null")
	private double interest;

	public Policies() {
		super();
	}

	public Policies(long policyId, @NotBlank(message = "policyName should not be blank") String policyName,
			@NotBlank(message = "policyDescription should not be blank") String policyDescription,
			@NotBlank(message = "company should not be blank") String company,
			@NotBlank(message = "policyType should not be blank") String policyType,
			@Positive(message = "initialDeposit should be positive value") double initialDeposit,
			@NotBlank(message = "termPeriod should not be blank") @Pattern(regexp = "^(Monthly|Quaterly|Half-Yearly|Annually)$") String termPeriod,
			@Positive(message = "termAmount should be positive value") double termAmount,
			@NotNull(message = "interest should not be null") double interest) {
		super();
		this.policyId = policyId;
		this.policyName = policyName;
		this.policyDescription = policyDescription;
		this.company = company;
		this.policyType = policyType;
		this.initialDeposit = initialDeposit;
		this.termPeriod = termPeriod;
		this.termAmount = termAmount;
		this.interest = interest;
	}

	public long getPolicyId() {
		return policyId;
	}

	public void setPolicyId(long policyId) {
		this.policyId = policyId;
	}

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	public String getPolicyDescription() {
		return policyDescription;
	}

	public void setPolicyDescription(String policyDescription) {
		this.policyDescription = policyDescription;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPolicyType() {
		return policyType;
	}

	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}

	public double getInitialDeposit() {
		return initialDeposit;
	}

	public void setInitialDeposit(double initialDeposit) {
		this.initialDeposit = initialDeposit;
	}

	public String getTermPeriod() {
		return termPeriod;
	}

	public void setTermPeriod(String termPeriod) {
		this.termPeriod = termPeriod;
	}

	public double getTermAmount() {
		return termAmount;
	}

	public void setTermAmount(double termAmount) {
		this.termAmount = termAmount;
	}

	public double getInterest() {
		return interest;
	}

	public void setInterest(double interest) {
		this.interest = interest;
	}

	@Override
	public String toString() {
		return "Policies [policyId=" + policyId + ", policyName=" + policyName + ", policyDescription="
				+ policyDescription + ", company=" + company + ", policyType=" + policyType + ", initialDeposit="
				+ initialDeposit + ", termPeriod=" + termPeriod + ", termAmount=" + termAmount + ", interest="
				+ interest + "]";
	}

}