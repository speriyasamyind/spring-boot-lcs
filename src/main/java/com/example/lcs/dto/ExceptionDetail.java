package com.example.lcs.dto;

import java.util.Date;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.ToString;

/**
 * The Exception details
 * 
 * @see "https://www.baeldung.com/global-error-handler-in-a-spring-rest-api"
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@ToString
@Data
public class ExceptionDetail {
  private int status;
  private String message;
  private String developerMessage;
  private String path;
  private Date timestamp;

  private ExceptionDetail() {
    timestamp = new Date();
  }

  public ExceptionDetail(int status, String message, String path, Throwable ex) {
    this();
    this.status = status;
    this.path = path;
    this.message = message;
  }


}
