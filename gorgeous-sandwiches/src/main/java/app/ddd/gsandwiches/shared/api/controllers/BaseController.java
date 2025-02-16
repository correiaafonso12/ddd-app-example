package app.ddd.gsandwiches.shared.api.controllers;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.internalServerError;

import org.springframework.http.ResponseEntity;

import app.ddd.gsandwiches.shared.api.dto.response.ErrorResponseDto;
import app.ddd.gsandwiches.shared.application.exceptions.ApplicationException;
import app.ddd.gsandwiches.shared.application.exceptions.ConflictException;

/**
 * Base Controller to centralize error handling without throwing custom
 * exceptions
 */
public abstract class BaseController {

    protected ResponseEntity<ErrorResponseDto> handleException(Exception e) {
        var dto = new ErrorResponseDto(e);

        // 409 Conflict
        if (e instanceof ConflictException) {
            return ResponseEntity.status(CONFLICT).body(dto);
        }
        // 400 Bad Request
        if (e instanceof ApplicationException) {
            return badRequest().body(dto);
        }
        // 500 Internal Server Error
        return internalServerError().body(dto);
    }

}
