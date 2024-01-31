package com.hexaware.policymanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.policymanager.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
