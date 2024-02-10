package com.hexaware.policymanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hexaware.policymanager.entities.Users;
@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
	public List<Users> getUserByUserType(String userType);
	public Users getUserByContactNumber(String contactNo);
	public Users getUserByEmailAddress(String emailAddress);
	@Query("select u.userId from Users u where u.emailAddress =?1")
	public long findUserIdByEmailAddress(String emailAddress);
	
	@Query("select u.firstName from Users u where u.emailAddress =?1")
	public String findUserNameByEmailAddress(String emailAddress);
	
	
	@Query("select u.userType from Users u where u.emailAddress =?1")
	public String findUserTypeByEmailAddress(String emailAddress);
	
}