package com.hexaware.policymanager.entities;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @JsonBackReference(value = "UserPolicies-Users")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "PolicyID")
    private Policies policy;

    @OneToMany(mappedBy = "userPolicy", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "UserPolicies-Claims")
    private List<Claims> claims;

    @NotNull(message = "Start date cannot be null")
    @FutureOrPresent(message = "Start date must be in the present or future")
    private Date startDate;

    private Date endDate;
    private double maturityAmount;

    @NotNull(message = "duration cannot be null")
    @Positive(message = "duration should be a positive value")
    private int durationInYears;

    public UserPolicies() {
        super();
    }

    // Constructor with policy initialization
    public UserPolicies(Users user, Policies policy, List<Claims> claims,
            @NotNull(message = "Start date cannot be null") @FutureOrPresent(message = "Start date must be in the present or future") Date startDate,
            Date endDate, double maturityAmount,
            @NotNull(message = "duration cannot be null") @Positive(message = "duration should be a positive value") int durationInYears) {
        super();
        this.user = user;
        this.policy = policy;
        this.claims = claims;
        this.startDate = startDate;
        this.endDate = endDate;
        this.maturityAmount = maturityAmount;
        this.durationInYears = durationInYears;
        calculateEndDate();
        calculateMaturityAmount();
    }

    // Getter and setters

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

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getDurationInYears() {
        return durationInYears;
    }
    

    public double getMaturityAmount() {
		return maturityAmount;
	}

	public void setMaturityAmount(double maturityAmount) {
		this.maturityAmount = maturityAmount;
	}

	public void setDurationInYears(int durationInYears) {
        this.durationInYears = durationInYears;
        calculateEndDate();
        calculateMaturityAmount();
    }

    // Calculate end date based on start date and duration
	private void calculateEndDate() {
	    if (startDate != null) {
	        // Convert java.sql.Date to java.util.Date
	        java.util.Date utilStartDate = new java.util.Date(startDate.getTime());
	        LocalDate localStartDate = utilStartDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	        LocalDate localEndDate = localStartDate.plusYears(durationInYears);
	        this.endDate = java.sql.Date.valueOf(localEndDate);
	    }
	}


    // Calculate maturity amount based on policy details and duration
    public void calculateMaturityAmount() {
        if (policy != null) {
            double initialDeposit = policy.getInitialDeposit();
            double termAmount = policy.getTermAmount();
            double interest = policy.getInterest();
            this.maturityAmount = initialDeposit
                    + (durationInYears * termAmount)
                    + ((durationInYears * termAmount) * (interest / 100));
        }
    }
}
