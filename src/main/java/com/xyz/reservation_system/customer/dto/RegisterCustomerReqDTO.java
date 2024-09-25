package com.xyz.reservation_system.customer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterCustomerReqDTO(
    @NotBlank(message = "Username is required")
        @Size(min = 8, message = "Username must be at least 8 characters long")
        String username,
    @NotBlank @Email(message = "Must be a valid email")String email,
    @NotBlank(message = "Password is required")
        @Size(min = 8, message = "Password must be at least 8 characters long")
        String password,
    @NotBlank(message = "name is required") String name,
    @NotBlank(message = "phone number is required") String phoneNumber) {}
