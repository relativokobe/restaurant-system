package com.xyz.reservation_system.common.exceptions;

import com.xyz.reservation_system.common.enums.ErrorCode;

/** Exception for any duplicate data */
public class DataAlreadyExistsException extends ErrorCodedException {
  public DataAlreadyExistsException(ErrorCode errorCode) {
    super(errorCode);
  }
}
