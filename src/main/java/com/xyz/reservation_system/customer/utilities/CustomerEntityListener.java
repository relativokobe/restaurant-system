package com.xyz.reservation_system.customer.utilities;

import com.xyz.reservation_system.common.configs.ApplicationContextProvider;
import com.xyz.reservation_system.common.utilities.IdGenerator;
import com.xyz.reservation_system.customer.entities.Customer;
import com.xyz.reservation_system.customer.repositories.CustomerRepository;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.PrePersist;

/** Entity Listener that handles the generating of Customer ID before saving the customer to DB */
public class CustomerEntityListener {

  /**
   * To be called before saving the Customer to DB to generate the customer ID
   *
   * @param customer the customer entity
   */
  @PrePersist
  public void prePersist(Customer customer) {
    if (StringUtils.isEmpty(customer.getCustomerId())) {
      final CustomerRepository customerRepository =
          (CustomerRepository)
              ApplicationContextProvider.getApplicationContext().getBean("customerRepository");

      final String customerId =
          IdGenerator.generateIdWithPrefix(
              "C-", customerRepository.getNextValMySequence("customer_id_seq"));
      customer.setCustomerId(customerId);
    }
  }
}
