package app.ddd.gsandwiches.sandwich.api.handlers;

import static org.springframework.http.ResponseEntity.badRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import app.ddd.gsandwiches.core.api.dto.ErrorResponseDto;

@ControllerAdvice
public class MethodArgumentNotValidExceptionHandler extends ResponseEntityExceptionHandler {

    private final String MISSING_FIELD_MESSAGE = "Field %s is mandatory";

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        var errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> MISSING_FIELD_MESSAGE.formatted(err.getField()))
                .toList();

        var body = new ErrorResponseDto(ex, errors);

        return badRequest().body(body);
    }

}
