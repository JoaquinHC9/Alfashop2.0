package com.pe.unmsm.fisi.alfashop.security.handler;

import com.pe.unmsm.fisi.alfashop.security.exception.UsuarioRegistradoExcepcion;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;


@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UsuarioRegistradoExcepcion.class)
    public ResponseEntity<ErrorResponse> handleUsuarioRegistradoExcepcion(UsuarioRegistradoExcepcion excepcion, HttpServletRequest request) {
        var errorResponse = ErrorResponse.builder()
                .message(excepcion.getMessage())
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.CONFLICT.value())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }
}
