package com.devsmile.springdata.dao.address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsmile.springdata.model.address.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
}
