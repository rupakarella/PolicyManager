package com.hexaware.policymanager.dto;

import java.util.List;

public class PoliciesDTO {
	private long policyId;
	private String policyName;
	private String policyDescription;
	private String company;
	private String policyType;
	private double initialDeposit;
	private String termPeriod;
	private double termAmount;
	private double interest;
	private List<String> eligibleUserTypes;

	public PoliciesDTO() {
		super();
	}

	
	public PoliciesDTO(long policyId, String policyName, String policyDescription, String company, String policyType,
			double initialDeposit, String termPeriod, double termAmount, double interest, List<String> eligibleUserTypes) {
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
		this.eligibleUserTypes = eligibleUserTypes;
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
	

	public List<String> getEligibleUserTypes() {
		return eligibleUserTypes;
	}


	public void setEligibleUserTypes(List<String> eligibleUserTypes) {
		this.eligibleUserTypes = eligibleUserTypes;
	}


	@Override
	public String toString() {
		return "PoliciesDTO [policyId=" + policyId + ", policyName=" + policyName + ", policyDescription="
				+ policyDescription + ", company=" + company + ", policyType=" + policyType + ", initialDeposit="
				+ initialDeposit + ", termPeriod=" + termPeriod + ", termAmount=" + termAmount + ", interest="
				+ interest + ", eligibleUserTypes=" + eligibleUserTypes + "]";
	}


	

}