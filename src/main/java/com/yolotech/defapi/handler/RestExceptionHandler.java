package com.yolotech.defapi.handler;

import com.yolotech.defapi.exceptions.BadRequestException;
import com.yolotech.defapi.exceptions.BadRequestExceptionDetails;
import com.yolotech.defapi.exceptions.ExceptionDetails;
import com.yolotech.defapi.exceptions.ValidationExceptionDetails;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Log4j2
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<BadRequestExceptionDetails> handleBadRequestException(
      BadRequestException badRequestException) {
    return new ResponseEntity<>(
        BadRequestExceptionDetails.builder()
            .timestamp(LocalDateTime.now())
            .status(HttpStatus.BAD_REQUEST.value())
            .title("Bad request exception. Please check the documentation.")
            .details(badRequestException.getMessage())
            .developerMessage(badRequestException.getClass().getName())
            .build(),
        HttpStatus.BAD_REQUEST);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException methodArgumentNotValidException,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    List<FieldError> fieldErrors =
        methodArgumentNotValidException.getBindingResult().getFieldErrors();
    String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(","));
    String fieldsMessage =
        fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));

    return new ResponseEntity<>(
        ValidationExceptionDetails.builder()
            .timestamp(LocalDateTime.now())
            .status(HttpStatus.BAD_REQUEST.value())
            .title("Bad request exception. Invalid fields.")
            .details("Please check input error(s).")
            .developerMessage(methodArgumentNotValidException.getClass().getName())
            .fields(fields)
            .fieldsMessage(fieldsMessage)
            .build(),
        HttpStatus.BAD_REQUEST);
  }

  @Override
  protected ResponseEntity<Object> handleExceptionInternal(
      Exception ex,
      @Nullable Object body,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {

    ExceptionDetails exceptionDetails =
        ExceptionDetails.builder()
            .timestamp(LocalDateTime.now())
            .status(status.value())
            .title(ex.getCause().getMessage())
            .details(ex.getMessage())
            .developerMessage(ex.getClass().getName())
            .build();

    return new ResponseEntity<>(exceptionDetails, headers, status);
  }
}
