package com.hexaware.policymanager.dto;

import java.util.List;

import com.hexaware.policymanager.entities.UserPolicies;

public class PoliciesDTO {
	private long policyId;
	private String policyName;
	private String policyDescription;
	private String company;
	private String policyType;
	private double maturityAmount;
	private double initialDeposit;
	private String termPeriod;
	private double termAmount;
	private double interest;
	private List<UserPolicies> userPolicies;

	public PoliciesDTO() {
		super();

	}

	public PoliciesDTO(long policyId, String policyName, String policyDescription, String company, String policyType,
			double maturityAmount, double initialDeposit, String termPeriod, double termAmount, double interest,
			List<UserPolicies> userPolicies) {
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
		return "PoliciesDTO [policyId=" + policyId + ", policyName=" + policyName + ", policyDescription="
				+ policyDescription + ", company=" + company + ", policyType=" + policyType + ", maturityAmount="
				+ maturityAmount + ", initialDeposit=" + initialDeposit + ", termPeriod=" + termPeriod + ", termAmount="
				+ termAmount + ", interest=" + interest + ", userPolicies=" + userPolicies + "]";
	}

	
}