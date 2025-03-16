package app.ddd.gsandwiches.core.api.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ErrorResponseDtoTest {

    private final String ERROR_MESSAGE = "smt";

    private Exception exception;

    @BeforeEach
    public void setup() {
        exception = new RuntimeException(ERROR_MESSAGE);
    }

    @Test
    void testCreateWithExceptionAndError() {
        var errors = List.of("custom_error_msg");
        var dto = new ErrorResponseDto(exception, errors);
        assertEquals(
                exception.getClass().getSimpleName(),
                dto.getException(),
                "DTO exception should be inputted exception");
        assertEquals(errors, dto.getErrors(), "DTO errors should be inputted errors");
    }

    @Test
    void testCreateWithException() {
        var dto = new ErrorResponseDto(exception);
        assertEquals(
                exception.getClass().getSimpleName(),
                dto.getException(),
                "DTO exception should be inputted exception");
        assertEquals(List.of(ERROR_MESSAGE), dto.getErrors(), "DTO error message should be message from exception");
    }
}
