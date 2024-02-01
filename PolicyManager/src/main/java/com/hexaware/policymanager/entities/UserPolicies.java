package com.hexaware.policymanager.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "UserPolicies")
public class UserPolicies {
	@Id
	@Column(name = "UserPolicyID")
	private long userPolicyId;

	@ManyToOne
	@JoinColumn(name = "UserID")
	private Users user;

	@ManyToOne
	@JoinColumn(name = "PolicyID")
	private Policies policy;

	@OneToMany(mappedBy = "userPolicy", cascade = CascadeType.ALL)
	private List<PolicyPayments> policyPayments;

	@NotNull
	@Column(name = "StartDate")
	private Date startDate;
	
	@NotNull
	@Column(name = "EndDate")
	private Date endDate;

	@Column(name = "DurationInYears")
	private int durationInYears;

	public UserPolicies() {
		super();
	}

	public UserPolicies(long userPolicyId, Users user, Policies policy, List<PolicyPayments> policyPayments,
			@NotNull Date startDate, @NotNull Date endDate, int durationInYears) {
		super();
		this.userPolicyId = userPolicyId;
		this.user = user;
		this.policy = policy;
		this.policyPayments = policyPayments;
		this.startDate = startDate;
		this.endDate = endDate;
		this.durationInYears = durationInYears;
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

	public List<PolicyPayments> getPolicyPayments() {
		return policyPayments;
	}

	public void setPolicyPayments(List<PolicyPayments> policyPayments) {
		this.policyPayments = policyPayments;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getDurationInYears() {
		return durationInYears;
	}

	public void setDurationInYears(int durationInYears) {
		this.durationInYears = durationInYears;
	}

	@Override
	public String toString() {
		return "UserPolicies [userPolicyId=" + userPolicyId + ", user=" + user + ", policy=" + policy
				+ ", policyPayments=" + policyPayments + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", durationInYears=" + durationInYears + "]";
	}

	

}