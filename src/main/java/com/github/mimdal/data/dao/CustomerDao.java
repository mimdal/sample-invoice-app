package com.github.mimdal.data.dao;

import javax.persistence.EntityNotFoundException;

import com.github.mimdal.data.entity.CustomerEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDao extends JpaRepository<CustomerEntity, Long> {

	default CustomerEntity findCustomerOrThrow(Long customerId) {
		return this.findById(customerId)
				.orElseThrow(() -> new EntityNotFoundException("customer not found! customerId: " + customerId));
	}

}
