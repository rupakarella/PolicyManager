package com.hexaware.policymanager.entities;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "UserPolicies")
public class UserPolicies {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userPolicyId;

	@ManyToOne
	@JoinColumn(name = "UserID")
	@JsonBackReference
	private Users user;

	@ManyToOne
	@JoinColumn(name = "PolicyID")
	//@JsonBackReference
	private Policies policy;

	@OneToMany(mappedBy = "userPolicy", cascade = CascadeType.ALL)
	//@JsonBackReference
	private List<PolicyPayments> policyPayments;

	@NotNull(message = "Start date cannot be null")
	@FutureOrPresent(message = "Start date must be in the present or future")
	private Date startDate;
	
	
	private Date endDate;

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


	public int getDurationInYears() {
		return durationInYears;
	}

	public void setDurationInYears(int durationInYears) {
		this.durationInYears = durationInYears;
		calculateEndDate();
	}	
		private void calculateEndDate() {

        if (startDate != null) {
            LocalDate localStartDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate localEndDate = localStartDate.plusYears(durationInYears);
            this.endDate = Date.from(localEndDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
	}

	@Override
	public String toString() {
		return "UserPolicies [userPolicyId=" + userPolicyId + ", user=" + user + ", policy=" + policy
				+ ", policyPayments=" + policyPayments + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", durationInYears=" + durationInYears + "]";
	}

	

}