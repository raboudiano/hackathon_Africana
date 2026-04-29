package com.Shadows.SpringZ.api;

import com.Shadows.SpringZ.api.dto.ApiErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.time.Instant;

/**
 * Centralizes error handling for the JSON API.
 *
 * Design choice:
 * - Service methods currently throw IllegalArgumentException for not-found cases.
 *   We translate that into a 404 to keep Postman testing straightforward.
 */
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiErrorResponse> handleIllegalArgument(
            IllegalArgumentException ex,
            HttpServletRequest request
    ) {
        return error(HttpStatus.NOT_FOUND, ex.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErrorResponse> handleNotReadable(
            HttpMessageNotReadableException ex,
            HttpServletRequest request
    ) {
        // Common when JSON contains invalid date formats or wrong types.
        return error(HttpStatus.BAD_REQUEST, "Invalid request body", request.getRequestURI());
    }

    private static ResponseEntity<ApiErrorResponse> error(
            HttpStatus status,
            String message,
            String path
    ) {
        ApiErrorResponse payload = new ApiErrorResponse(
                Instant.now(),
                status.value(),
                status.getReasonPhrase(),
                message,
                path
        );
        return ResponseEntity.status(status).body(payload);
    }
}
