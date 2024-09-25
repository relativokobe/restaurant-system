package com.xyz.reservation_system.common.enums;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
  RESERVATION_NOT_FOUND("RESERVATION_NOT_FOUND", HttpStatus.BAD_REQUEST),
  PHONE_NUMBER_ALREADY_EXISTS("PHONE_NUMBER_ALREADY_EXISTS", HttpStatus.BAD_REQUEST),
  USERNAME_DOES_NOT_EXIST("USERNAME_DOES_NOT_EXIST", HttpStatus.FORBIDDEN),
  WRONG_PASSWORD("WRONG_PASSWORD", HttpStatus.FORBIDDEN),
  FIELD_VALIDATION_ERROR("FIELD_VALIDATION_ERROR", HttpStatus.BAD_REQUEST),
  AUTHENTICATION_EXCEPTION("Authentication Exception", HttpStatus.UNAUTHORIZED),
  EMAIL_ALREADY_EXISTS("Email already exists", HttpStatus.BAD_REQUEST),
  USERNAME_ALREADY_EXISTS("Username already exists", HttpStatus.BAD_REQUEST),
  CURRENT_CUSTOMER_NOT_FOUND("CURRENT_USER_NOT_FOUND", HttpStatus.BAD_REQUEST),
  GENERIC_ERROR("Generic error encountered", HttpStatus.INTERNAL_SERVER_ERROR);

  private final String errorMessage;
  private final HttpStatus httpStatus;

  ErrorCode(String errorMessage, HttpStatus httpStatus) {
    this.errorMessage = errorMessage;
    this.httpStatus = httpStatus;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public HttpStatus getHttpStatus() {
    return httpStatus;
  }
}
