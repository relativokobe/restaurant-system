package com.xyz.reservation_system.common.exceptions;

import com.xyz.reservation_system.common.enums.ErrorCode;
import java.util.Map;

/** Parent exception that stores the errorCode and failedParameters if there are any */
public class ErrorCodedException extends RuntimeException {
  private final ErrorCode errorCode;
  private Map<String, String> failedParameters;

  public ErrorCodedException(ErrorCode errorCode) {
    this.errorCode = errorCode;
  }

  public ErrorCodedException(ErrorCode errorCode, Map<String, String> failedParameters) {
    this.errorCode = errorCode;
    this.failedParameters = failedParameters;
  }

  public ErrorCode getErrorCode() {
    return errorCode;
  }

  public Map<String, String> getFailedParameters() {
    return failedParameters;
  }
}
