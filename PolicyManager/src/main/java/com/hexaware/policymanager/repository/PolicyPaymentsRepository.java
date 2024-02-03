package com.hexaware.policymanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.policymanager.entities.PolicyPayments;

public interface PolicyPaymentsRepository extends JpaRepository<PolicyPayments, Long> {

<<<<<<< HEAD
=======
	public void deleteByTransactionId(long txnId);

>>>>>>> 1b8d09207d89d7b59b3209431beffae97408e7db
}
