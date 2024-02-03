package com.hexaware.policymanager.repository;

<<<<<<< HEAD
=======
import java.util.List;

>>>>>>> 1b8d09207d89d7b59b3209431beffae97408e7db
import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.policymanager.entities.Policies;

public interface PoliciesRepository extends JpaRepository<Policies, Long> {

<<<<<<< HEAD
=======
	List<Policies> findByCompany(String company);

	List<Policies> findByAmountLessThan(double amount);

	List<Policies> findByAmountGreaterThan(double amount);

	List<Policies> findByPolicyType(String policyType);

>>>>>>> 1b8d09207d89d7b59b3209431beffae97408e7db
}
