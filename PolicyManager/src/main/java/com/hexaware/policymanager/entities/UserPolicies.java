package com.hexaware.policymanager.entities;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "UserPolicies")
public class UserPolicies {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userPolicyId;

	@ManyToOne
	@JoinColumn(name = "UserID")
	@JsonBackReference(value="UserPolicies-Users")
	private Users user;

	@ManyToOne
	@JoinColumn(name = "PolicyID")
	@JsonBackReference(value="UserPolicies-Policies")
	private Policies policy;

	@OneToMany(mappedBy = "userPolicy", cascade = CascadeType.ALL)
	@JsonManagedReference(value = "UserPolicies-Claims")
	private List<Claims> claims;

	@NotNull(message = "Start date cannot be null")
	@FutureOrPresent(message = "Start date must be in the present or future")
	private Date startDate;
	
	
	private Date endDate;

	@NotNull(message = "duration cannot be null")
	@Positive(message="duration should be a positive value")
	private int durationInYears;

	public UserPolicies() {
		super();
	}

	public UserPolicies(long userPolicyId, Users user, Policies policy, List<Claims> claims,
			@NotNull(message = "Start date cannot be null") @FutureOrPresent(message = "Start date must be in the present or future") Date startDate,
			Date endDate,
			@NotNull(message = "duration cannot be null") @Positive(message = "duration should be a positive value") int durationInYears) {
		super();
		this.userPolicyId = userPolicyId;
		this.user = user;
		this.policy = policy;
		this.claims = claims;
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

	@JsonIgnore
	public List<Claims> getClaims() {
		return claims;
	}

	public void setClaims(List<Claims> claims) {
		this.claims = claims;
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
				+ ", claims=" + claims + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", durationInYears=" + durationInYears + "]";
	}

	

}