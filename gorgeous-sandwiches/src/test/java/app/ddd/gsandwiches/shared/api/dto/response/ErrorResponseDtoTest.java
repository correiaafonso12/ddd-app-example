package app.ddd.gsandwiches.shared.api.dto.response;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ErrorResponseDtoTest {

    private final String ERROR_MESSAGE = "smt";

    private Exception exception;

    @BeforeEach
    public void setup() {
        exception = new NullPointerException(ERROR_MESSAGE);
    }

    @Test
    void testCreateWithExceptionAndError() {
        var errorMessage = "custom_error_msg";
        var dto = new ErrorResponseDto(exception, errorMessage);
        assertEquals(
                exception.getClass().getSimpleName(),
                dto.getException(),
                "Created dto exception should be inputted exception");
        assertEquals(errorMessage, dto.getError(), "Created dto error message should be inputted error message");
    }

    @Test
    void testCreateWithException() {
        var dto = new ErrorResponseDto(exception);
        assertEquals(
                exception.getClass().getSimpleName(),
                dto.getException(),
                "Created dto exception should be inputted exception");
        assertEquals(ERROR_MESSAGE, dto.getError(), "Created dto error message should be message from exception");
    }
}
