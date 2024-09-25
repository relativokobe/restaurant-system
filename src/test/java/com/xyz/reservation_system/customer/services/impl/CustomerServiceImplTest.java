package com.xyz.reservation_system.customer.services.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import com.xyz.reservation_system.common.exceptions.DataAlreadyExistsException;
import com.xyz.reservation_system.customer.dto.RegisterCustomerReqDTO;
import com.xyz.reservation_system.customer.dto.RegisterCustomerRespDTO;
import com.xyz.reservation_system.customer.entities.Customer;
import com.xyz.reservation_system.customer.repositories.CustomerRepository;
import com.xyz.reservation_system.customer.utilities.CustomerMapper;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;

class CustomerServiceImplTest {
  CustomerServiceImpl customerService;
  CustomerRepository repository;
  CustomerMapper mapper;

  @BeforeEach
  public void setUp() {
    repository = Mockito.mock(CustomerRepository.class);
    mapper = Mockito.mock(CustomerMapper.class);
    customerService = new CustomerServiceImpl(repository, mapper);
  }

  @Test
  public void testRegister() throws ExecutionException, InterruptedException {
    final Customer customer = new Customer();
    customer.setCustomerId("sampleId");
    Mockito.when(repository.save(any())).thenReturn(customer);
    Mockito.when(mapper.convertRequestToEntity(any(), any(), any())).thenReturn(customer);

    final RegisterCustomerRespDTO response =
        customerService
            .register(
                new RegisterCustomerReqDTO(
                    "username", "asd@gmail.com", "samplePassword", "sampleName", "1234567"))
            .get();
    assertEquals(customer.getCustomerId(), response.customerId());
  }

  @Test
  public void testRegisterNotUniqueEmail() {
    final RegisterCustomerReqDTO request =
        new RegisterCustomerReqDTO(
            "username", "asd@mail.com", "samplePassword", "sampleName", "1234567");

    final Customer customer = new Customer();
    customer.setCustomerId("sampleId");
    final DataIntegrityViolationException exception =
        new DataIntegrityViolationException("customer_email_key");
    Mockito.when(repository.save(any())).thenThrow(exception);
    Mockito.when(mapper.convertRequestToEntity(any(), any(), any())).thenReturn(customer);

    assertThrows(DataAlreadyExistsException.class, () -> customerService.register(request));
  }

  @Test
  public void testRegisterNotUniqueUsername() {
    final RegisterCustomerReqDTO request =
        new RegisterCustomerReqDTO(
            "username", "asdmail.com", "samplePassword", "sampleName", "1234567");

    final Customer customer = new Customer();
    customer.setCustomerId("sampleId");
    final DataIntegrityViolationException exception =
        new DataIntegrityViolationException("customer_username_key");
    Mockito.when(repository.save(any())).thenThrow(exception);
    Mockito.when(mapper.convertRequestToEntity(any(), any(), any())).thenReturn(customer);

    assertThrows(DataAlreadyExistsException.class, () -> customerService.register(request));
  }

  @Test
  public void testRegisterNotUniquePhone() {
    final RegisterCustomerReqDTO request =
        new RegisterCustomerReqDTO(
            "username", "asdmail.com", "samplePassword", "sampleName", "1234567");

    final Customer customer = new Customer();
    customer.setCustomerId("sampleId");
    final DataIntegrityViolationException exception =
        new DataIntegrityViolationException("customer_phone_number_key");
    Mockito.when(repository.save(any())).thenThrow(exception);
    Mockito.when(mapper.convertRequestToEntity(any(), any(), any())).thenReturn(customer);

    assertThrows(DataAlreadyExistsException.class, () -> customerService.register(request));
  }

  @Test
  public void testRegisterNotGenericError() {
    final RegisterCustomerReqDTO request =
        new RegisterCustomerReqDTO(
            "username", "asdmail.com", "samplePassword", "sampleName", "1234567");

    final Customer customer = new Customer();
    customer.setCustomerId("sampleId");
    final DataIntegrityViolationException exception = new DataIntegrityViolationException("");
    Mockito.when(repository.save(any())).thenThrow(exception);
    Mockito.when(mapper.convertRequestToEntity(any(), any(), any())).thenReturn(customer);

    assertThrows(DataAlreadyExistsException.class, () -> customerService.register(request));
  }
}
