package com.xyz.reservation_system.customer.controllers;

import com.xyz.reservation_system.customer.dto.RegisterCustomerReqDTO;
import com.xyz.reservation_system.customer.dto.RegisterCustomerRespDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.concurrent.CompletableFuture;

@Tag(name = "Customer API")
public interface CustomerController {
  @Operation(summary = "Register Customer", description = "This API is called to register Customer")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully registered new Customer",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = RegisterCustomerRespDTO.class))
            }),
        @ApiResponse(responseCode = "400", description = "Invalid Params", content = @Content),
        @ApiResponse(
            responseCode = "403",
            description = "Request not Authorize",
            content = @Content),
        @ApiResponse(
            responseCode = "500",
            description = "Internal Server Error",
            content = @Content),
      })
  CompletableFuture<RegisterCustomerRespDTO> register(RegisterCustomerReqDTO request);
}
