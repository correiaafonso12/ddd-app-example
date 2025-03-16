package app.ddd.gsandwiches.core.api.handler;

import org.springframework.http.ResponseEntity;

import app.ddd.gsandwiches.core.api.dto.ErrorResponseDto;

/**
 * Handles application exceptions
 */
public interface ExceptionHandler {

    /**
     * Creates an error response based on an exception
     * 
     * @param e catched exception
     * @return {@code ResponseEntity} containing the appropriate status code
     */
    public ResponseEntity<ErrorResponseDto> handle(Exception e);
}
