package com.example.lcs.exception;

import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.lcs.dto.ExceptionDetail;


/**
 * Application Exception Handler
 * 
 * @see https://www.baeldung.com/exception-handling-for-rest-with-spring
 */
@RestControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request) {
    ExceptionDetail ed = new ExceptionDetail(HttpStatus.BAD_REQUEST.value(), "Bad Data in the Request",
        getRequestURI(request), ex);
    return ResponseEntity.badRequest().body(ed);
  }

  @Override
  protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {
    ExceptionDetail ed = new ExceptionDetail(HttpStatus.METHOD_NOT_ALLOWED.value(), "Request Method Not Supported",
        getRequestURI(request), ex);
    return ResponseEntity.badRequest().body(ed);
  }
  
  @ExceptionHandler(AppException.class)
  public final ResponseEntity<Object> handleApplicationException(AppException ex, WebRequest request) {
    ExceptionDetail ed = new ExceptionDetail(ex.statusCode, ex.errorMessage, getRequestURI(request), ex);
    return ResponseEntity.status(HttpStatus.resolve(ex.statusCode)).body(ed);
  }

  private String getRequestURI(@NotNull WebRequest request) {
    HttpServletRequest hsr = ((ServletWebRequest) request).getRequest();
    return hsr.getRequestURI();
  }

}
