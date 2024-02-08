package com.hexaware.policymanager.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "Policies")
public class Policies {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long policyId;

	@NotBlank
	private String policyName;
	
	@NotBlank
	private String policyDescription;

	@NotBlank
	private String company;

	@NotBlank
	private String policyType;

	@Positive
	private double maturityAmount;

	@Positive
	private double initialDeposit;

	@NotBlank
	@Pattern(regexp ="^(Monthly|Quaterly|Half-Yearly|Annually)$")
	private String termPeriod;

	@Positive
	private double termAmount;

	private double interest;

	@OneToMany(mappedBy = "policy", cascade = CascadeType.ALL)
	private List<UserPolicies> userPolicies;

	public Policies() {
		super();
	}

	public Policies(long policyId, @NotBlank String policyName, @NotBlank String policyDescription,
			@NotBlank String company, @NotBlank String policyType, @Positive double maturityAmount,
			@Positive double initialDeposit,
			@NotBlank @Pattern(regexp = "^(Monthly|Quaterly|Half-Yearly|Annually)$") String termPeriod,
			@Positive double termAmount, double interest, List<UserPolicies> userPolicies) {
		super();
		this.policyId = policyId;
		this.policyName = policyName;
		this.policyDescription = policyDescription;
		this.company = company;
		this.policyType = policyType;
		this.maturityAmount = maturityAmount;
		this.initialDeposit = initialDeposit;
		this.termPeriod = termPeriod;
		this.termAmount = termAmount;
		this.interest = interest;
		this.userPolicies = userPolicies;
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

	public double getMaturityAmount() {
		return maturityAmount;
	}

	public void setMaturityAmount(double maturityAmount) {
		this.maturityAmount = maturityAmount;
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

	public List<UserPolicies> getUserPolicies() {
		return userPolicies;
	}

	public void setUserPolicies(List<UserPolicies> userPolicies) {
		this.userPolicies = userPolicies;
	}

	@Override
	public String toString() {
		return "Policies [policyId=" + policyId + ", policyName=" + policyName + ", policyDescription="
				+ policyDescription + ", company=" + company + ", policyType=" + policyType + ", maturityAmount="
				+ maturityAmount + ", initialDeposit=" + initialDeposit + ", termPeriod=" + termPeriod + ", termAmount="
				+ termAmount + ", interest=" + interest + ", userPolicies=" + userPolicies + "]";
	}

	
	

}