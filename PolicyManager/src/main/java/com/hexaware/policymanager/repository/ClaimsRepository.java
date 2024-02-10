package com.hexaware.policymanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.policymanager.entities.Claims;
@Repository
public interface ClaimsRepository extends JpaRepository<Claims, Integer> {

	List<Claims> findByClaimAmount(double claimAmount);

	List<Claims> findByClaimStatus(String claimStatus);

}
