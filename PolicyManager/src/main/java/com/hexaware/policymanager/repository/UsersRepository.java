package com.hexaware.policymanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.policymanager.entities.Users;
@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
	public List<Users> getUserByUserType(String userType);
	public Users getUserBycontactNo(String contactNo);
	public Users getUserByEmailAddress(String emailAddress);
}
