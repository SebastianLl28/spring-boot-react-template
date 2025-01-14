package com.app.backend.configuration.app;

import com.app.backend.presentation.dto.ErrorResponse;
import jakarta.persistence.EntityNotFoundException;
import java.nio.file.AccessDeniedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException.Forbidden;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

//  @ExceptionHandler(value = RuntimeException.class)
//  public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException e) {
//    e.printStackTrace();
//    return ResponseEntity.badRequest().body(
//        new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value())
//    );
//  }

//  @ExceptionHandler(value = EntityNotFoundException.class)
//  public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException e) {
//    return ResponseEntity.status(HttpStatus.NOT_FOUND)
//        .body(new ErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND.value()));
//  }
//
//  @ExceptionHandler(value = Forbidden.class)
//  public ResponseEntity<ErrorResponse> handleForbiddenException(Forbidden e) {
//    return ResponseEntity.status(HttpStatus.FORBIDDEN)
//        .body(new ErrorResponse("Access denied: " + e.getMessage(), HttpStatus.FORBIDDEN.value()));
//  }
//
//  @ExceptionHandler(value = IllegalArgumentException.class)
//  public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException e) {
//    return ResponseEntity.badRequest()
//        .body(new ErrorResponse("Invalid argument: " + e.getMessage(), HttpStatus.BAD_REQUEST.value()));
//  }
//
//  @ExceptionHandler(value = NullPointerException.class)
//  public ResponseEntity<ErrorResponse> handleNullPointerException(NullPointerException e) {
//    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//        .body(new ErrorResponse("A server error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()));
//  }
//
//  @ExceptionHandler(value = AccessDeniedException.class)
//  public ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException e) {
//    return ResponseEntity.status(HttpStatus.FORBIDDEN)
//        .body(new ErrorResponse("Access denied: " + e.getMessage(), HttpStatus.FORBIDDEN.value()));
//  }
}
