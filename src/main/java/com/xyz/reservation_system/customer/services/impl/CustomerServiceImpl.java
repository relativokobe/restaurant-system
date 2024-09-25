package com.xyz.reservation_system.customer.services.impl;

import com.xyz.reservation_system.common.enums.ErrorCode;
import com.xyz.reservation_system.common.exceptions.DataAlreadyExistsException;
import com.xyz.reservation_system.customer.dto.RegisterCustomerReqDTO;
import com.xyz.reservation_system.customer.dto.RegisterCustomerRespDTO;
import com.xyz.reservation_system.customer.entities.Customer;
import com.xyz.reservation_system.customer.enums.CustomerStatus;
import com.xyz.reservation_system.customer.repositories.CustomerRepository;
import com.xyz.reservation_system.customer.services.CustomerService;
import com.xyz.reservation_system.customer.utilities.CustomerMapper;
import java.time.ZonedDateTime;
import java.util.concurrent.CompletableFuture;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/** Service that handles the business logic of Customer related tasks */
@Service
public class CustomerServiceImpl implements CustomerService {
  private final CustomerRepository repository;
  private final CustomerMapper mapper;

  public CustomerServiceImpl(CustomerRepository repository, CustomerMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  /**
   * Method that handles the business logic of registering the customer
   *
   * @param request contains the data needed to register the customer
   * @return RegisterCustomerRespDTO contains the customerId
   */
  @Async
  @Override
  public CompletableFuture<RegisterCustomerRespDTO> register(RegisterCustomerReqDTO request) {
    final Customer customer =
        mapper.convertRequestToEntity(request, CustomerStatus.ACTIVE, ZonedDateTime.now());
    try {
      repository.save(customer);
      return CompletableFuture.completedFuture(
          new RegisterCustomerRespDTO(customer.getCustomerId()));
    } catch (DataIntegrityViolationException e) {
      throw new DataAlreadyExistsException(identifyCorrectErrorCode(e));
    }
  }

  /**
   * This method identifies the correct error code when a violation occurs when saving the customer
   *
   * @param e DataIntegrityViolationException thrown on DB saving
   * @return the correct ErrorCode
   */
  private ErrorCode identifyCorrectErrorCode(DataIntegrityViolationException e) {
    ErrorCode errorCode;
    final String message = e.getMessage();
    if (message.contains("customer_email_key")) {
      errorCode = ErrorCode.EMAIL_ALREADY_EXISTS;
    } else if (message.contains("customer_username_key")) {
      errorCode = ErrorCode.USERNAME_ALREADY_EXISTS;
    } else if (message.contains("customer_phone_number_key")) {
      errorCode = ErrorCode.PHONE_NUMBER_ALREADY_EXISTS;
    } else {
      errorCode = ErrorCode.GENERIC_ERROR;
    }

    return errorCode;
  }
}
