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
	@Column(name = "RegistrationDate")
	private Date registrationDate;

	@NotNull
	@Column(name = "StartDate")
	private Date startDate;

	@Column(name = "DurationInYears")
	private int durationInYears;

	public UserPolicies() {
		super();
	}

	public UserPolicies(long userPolicyId, Users user, Policies policy, Date registrationDate, Date startDate,
			int durationInYears) {
		super();
		this.userPolicyId = userPolicyId;
		this.user = user;
		this.policy = policy;
		this.registrationDate = registrationDate;
		this.startDate = startDate;
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

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public int getDurationInYears() {
		return durationInYears;
	}

	public void setDurationInYears(int durationInYears) {
		this.durationInYears = durationInYears;
	}

	@Override
	public String toString() {
		return "User_Policy [userPolicyId=" + userPolicyId + ", user=" + user + ", policy=" + policy
				+ ", policyPayments=" + policyPayments + ", registrationDate=" + registrationDate + ", startDate="
				+ startDate + ", durationInYears=" + durationInYears + "]";
	}

}