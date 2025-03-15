package app.ddd.gsandwiches.shared.api.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Common response DTO for error messages
 */
public class ErrorResponseDto {

    private static final String DEFAULT_ERROR_MESSAGE = "An error occurred";

    private final String timestamp;
    private final String exception;
    private final List<String> errors;

    public ErrorResponseDto(Exception e, List<String> errors) {
        timestamp = LocalDateTime.now().toString();
        exception = e.getClass().getSimpleName();
        this.errors = errors;
    }

    public ErrorResponseDto(Exception e) {
        this(e, List.of(Optional.ofNullable(e.getMessage()).orElse(DEFAULT_ERROR_MESSAGE)));
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getException() {
        return exception;
    }

    public List<String> getErrors() {
        return errors;
    }
}
