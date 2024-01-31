package com.hexaware.policymanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.policymanager.entities.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {

}
