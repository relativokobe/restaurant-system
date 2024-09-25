package com.xyz.reservation_system.common.dto;

import java.util.Map;
import org.springframework.http.HttpStatus;

public record ErrorDTO(
    HttpStatus httpStatus,
    String message,
    String errorCode,
    Map<String, String> failedParameters) {}
