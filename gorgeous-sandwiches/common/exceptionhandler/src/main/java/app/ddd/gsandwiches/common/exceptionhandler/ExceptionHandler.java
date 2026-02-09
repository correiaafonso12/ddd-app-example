package app.ddd.gsandwiches.common.exceptionhandler;

import org.springframework.http.ResponseEntity;

import app.ddd.gsandwiches.common.exceptionhandler.dto.ErrorResponseDto;

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
