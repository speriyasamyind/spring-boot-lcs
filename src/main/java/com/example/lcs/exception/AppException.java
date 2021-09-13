package com.example.lcs.exception;

import org.springframework.http.HttpStatus;

public class AppException extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = 3526824377801636203L;

  /** The status code. */
  public final int statusCode;

  /** The error message. */
  public String errorMessage;

  /** The error message. */
  public String developerMessage;

  public AppException(HttpStatus httpStatus, Throwable throwable) {
    super(httpStatus.getReasonPhrase(), throwable);
    this.statusCode = httpStatus.value();
    this.developerMessage = throwable.getLocalizedMessage();
  }

  public AppException(HttpStatus httpStatus, String errorMessage) {
    super(errorMessage);
    this.statusCode = httpStatus.value();
    this.errorMessage = errorMessage;
  }

}
