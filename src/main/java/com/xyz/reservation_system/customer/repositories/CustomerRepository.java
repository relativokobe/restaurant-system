package com.xyz.reservation_system.customer.repositories;

import com.xyz.reservation_system.common.repositories.SequenceRepository;
import com.xyz.reservation_system.customer.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>, SequenceRepository {
  Optional<Customer> findByCustomerId(String customerId);
}
