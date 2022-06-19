package com.seogineer.springblockfileextensions.common.advice;

import com.seogineer.springblockfileextensions.common.exception.ExtensionNotFoundException;
import com.seogineer.springblockfileextensions.common.exception.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ExtensionNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleExtensionNotFountException(ExtensionNotFoundException ex) {
        return ResponseEntity.internalServerError().body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()));
    }
}
