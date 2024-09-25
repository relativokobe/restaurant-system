package com.xyz.reservation_system.common.exceptions;

import com.xyz.reservation_system.common.enums.ErrorCode;

/** Exception for any data that is not found */
public class NoRecordsFoundException extends ErrorCodedException {
  public NoRecordsFoundException(ErrorCode errorCode) {
    super(errorCode);
  }
}
