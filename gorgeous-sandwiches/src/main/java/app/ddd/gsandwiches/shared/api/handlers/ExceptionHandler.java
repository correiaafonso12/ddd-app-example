package app.ddd.gsandwiches.shared.api.handlers;

import org.springframework.http.ResponseEntity;

import app.ddd.gsandwiches.shared.api.dto.response.ErrorResponseDto;

/**
 * Handle how error responses should be created based on catched exception
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
