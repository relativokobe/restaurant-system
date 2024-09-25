package com.xyz.reservation_system.customer.dto;

public record CustomerBookerDTO(
    String customerId, String name, String email, String phoneNumber, String username) {}
