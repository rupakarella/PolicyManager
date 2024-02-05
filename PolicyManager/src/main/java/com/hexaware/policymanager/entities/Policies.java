package com.hexaware.policymanager.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "Policies")
public class Policies {

	@Id
	@Column(name = "PolicyID")
	private long policyId;

	@NotEmpty
	@Column(name = "PolicyName")
	private String policyName;
	
	@Column(name="PolicyDescription")
	private String policyDescription;

	@NotEmpty
	@Column(name = "Company")
	private String company;

	@NotEmpty
	@Column(name = "PolicyType")
	private String policyType;

	@Column(name = "MaturityAmount")
	private Double maturityAmount;

	@Column(name = "InitialDeposit")
	private Double initialDeposit;

	@NotEmpty
	@Pattern(regexp ="^(Monthly|Quaterly|Half-Yearly|Annually)$")
	@Column(name = "TermPeriod")
	private String termPeriod;

	@Positive
	@Column(name = "TermAmount")
	private Double termAmount;

	@Column(name = "Interest")
	private Double interest;

	@OneToMany(mappedBy = "policy", cascade = CascadeType.ALL)
	private List<UserPolicies> userPolicies;

	public Policies() {
		super();
	}

	
	public Policies(long policyId, @NotEmpty String policyName, String policyDescription, @NotEmpty String company,
			@NotEmpty String policyType, Double maturityAmount, Double initialDeposit,
			@NotEmpty @Pattern(regexp = "^(Monthly|Quaterly|Half-Yearly|Annually)$") String termPeriod,
			Double termAmount, Double interest, List<UserPolicies> userPolicies) {
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


	public Double getMaturityAmount() {
		return maturityAmount;
	}


	public void setMaturityAmount(Double maturityAmount) {
		this.maturityAmount = maturityAmount;
	}


	public Double getInitialDeposit() {
		return initialDeposit;
	}


	public void setInitialDeposit(Double initialDeposit) {
		this.initialDeposit = initialDeposit;
	}


	public String getTermPeriod() {
		return termPeriod;
	}


	public void setTermPeriod(String termPeriod) {
		this.termPeriod = termPeriod;
	}


	public Double getTermAmount() {
		return termAmount;
	}


	public void setTermAmount(Double termAmount) {
		this.termAmount = termAmount;
	}


	public Double getInterest() {
		return interest;
	}


	public void setInterest(Double interest) {
		this.interest = interest;
	}


	public List<UserPolicies> getUserPolicies() {
		return userPolicies;
	}


	public void setUserPolicies(List<UserPolicies> userPolicies) {
		this.userPolicies = userPolicies;
	}


}