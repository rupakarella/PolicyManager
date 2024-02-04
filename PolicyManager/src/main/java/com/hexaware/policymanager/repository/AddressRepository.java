package com.hexaware.policymanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.policymanager.entities.Address;
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
	public List<Address> getByCity(String city);
	public List<Address> getByState(String state);
}
