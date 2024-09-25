package com.xyz.reservation_system.common.controllers;

import com.xyz.reservation_system.common.dto.ErrorDTO;
import com.xyz.reservation_system.common.enums.ErrorCode;
import com.xyz.reservation_system.common.exceptions.ErrorCodedException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationExceptionHandler {

  /**
   * Handles all ErrorCodedException and its children
   *
   * @param e ErrorCodedException
   * @return Response object with ErrorDTO as body
   */
  @ExceptionHandler(ErrorCodedException.class)
  public ResponseEntity<ErrorDTO> handle(ErrorCodedException e) {
    ErrorCode errorCode = e.getErrorCode();
    return ResponseEntity.status(errorCode.getHttpStatus())
        .body(
            new ErrorDTO(
                errorCode.getHttpStatus(),
                errorCode.getErrorMessage(),
                errorCode.name(),
                e.getFailedParameters()));
  }

  /**
   * Handles all MethodArgumentNotValidException mostly used in validation
   *
   * @param e MethodArgumentNotValidException
   * @return Response object with ErrorDTO as body
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorDTO> handle(MethodArgumentNotValidException e) {
    ErrorCode errorCode = ErrorCode.FIELD_VALIDATION_ERROR;
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(
            new ErrorDTO(
                HttpStatus.BAD_REQUEST,
                errorCode.getErrorMessage(),
                errorCode.name(),
                getFailedParameters(e)));
  }

  /**
   * Handle generic exceptions that are unexpected
   *
   * @param e Exception
   * @return Response object with ErrorDTO as body
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorDTO> handle(Exception e) {
    ErrorCode errorCode = ErrorCode.GENERIC_ERROR;
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(
            new ErrorDTO(
                HttpStatus.INTERNAL_SERVER_ERROR,
                errorCode.getErrorMessage(),
                errorCode.name(),
                null));
  }

  /**
   * Used to retrieve failed parameters on MethodArgumentNotValidException
   *
   * @param e MethodArgumentNotValidException
   * @return The Map that contains the failedParameter and its corresponding error message
   */
  private Map<String, String> getFailedParameters(MethodArgumentNotValidException e) {
    HashMap<String, String> failedParameterMap = new HashMap<>();
    e.getBindingResult()
        .getFieldErrors()
        .forEach(
            fieldError ->
                failedParameterMap.put(fieldError.getField(), fieldError.getDefaultMessage()));

    return failedParameterMap;
  }
}
