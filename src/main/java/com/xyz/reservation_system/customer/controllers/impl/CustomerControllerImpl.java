package com.xyz.reservation_system.customer.controllers.impl;

import com.xyz.reservation_system.customer.controllers.CustomerController;
import com.xyz.reservation_system.customer.dto.RegisterCustomerReqDTO;
import com.xyz.reservation_system.customer.dto.RegisterCustomerRespDTO;
import com.xyz.reservation_system.customer.services.CustomerService;
import jakarta.validation.Valid;
import java.util.concurrent.CompletableFuture;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Controller that handles the requests related to Customer */
@RestController
@RequestMapping("/api/customer")
public class CustomerControllerImpl implements CustomerController {
  private final CustomerService customerService;

  public CustomerControllerImpl(CustomerService customerService) {
    this.customerService = customerService;
  }

  /**
   * Method called when an incoming request to register customer
   *
   * @param request containing the info needed for registration
   * @return RegisterCustomerRespDTO contains the customerId
   */
  @PostMapping
  @Override
  public CompletableFuture<RegisterCustomerRespDTO> register(
      @RequestBody @Valid RegisterCustomerReqDTO request) {
    return customerService.register(request);
  }
}
