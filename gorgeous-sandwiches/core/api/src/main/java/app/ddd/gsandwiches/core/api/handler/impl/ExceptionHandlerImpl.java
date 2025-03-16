package app.ddd.gsandwiches.core.api.handler.impl;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.internalServerError;
import static org.springframework.http.ResponseEntity.status;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import app.ddd.gsandwiches.core.api.dto.ErrorResponseDto;
import app.ddd.gsandwiches.core.api.handler.ExceptionHandler;
import app.ddd.gsandwiches.core.exceptions.ApplicationException;
import app.ddd.gsandwiches.core.exceptions.ConflictException;

@Component
class ExceptionHandlerImpl implements ExceptionHandler {

    @Override
    public ResponseEntity<ErrorResponseDto> handle(Exception e) {
        var dto = new ErrorResponseDto(e);

        // 409 Conflict
        if (e instanceof ConflictException) {
            return status(CONFLICT).body(dto);
        }
        // 400 Bad Request
        if (e instanceof ApplicationException) {
            return badRequest().body(dto);
        }
        // 500 Internal Server Error
        return internalServerError().body(dto);
    }

}
