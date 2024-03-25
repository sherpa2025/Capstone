package com.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
