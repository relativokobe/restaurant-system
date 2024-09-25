package com.xyz.reservation_system.customer.services;

import com.xyz.reservation_system.customer.dto.RegisterCustomerReqDTO;
import com.xyz.reservation_system.customer.dto.RegisterCustomerRespDTO;
import java.util.concurrent.CompletableFuture;

public interface CustomerService {
  CompletableFuture<RegisterCustomerRespDTO> register(RegisterCustomerReqDTO request);
}
