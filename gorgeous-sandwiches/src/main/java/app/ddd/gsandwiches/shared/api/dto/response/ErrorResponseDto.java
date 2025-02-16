package app.ddd.gsandwiches.shared.api.dto.response;

import java.time.LocalDateTime;

public class ErrorResponseDto {

    private final String timestamp;
    private final String exception;
    private final String error;

    public ErrorResponseDto(Exception e, String error) {
        timestamp = LocalDateTime.now().toString();
        exception = e.getClass().getSimpleName();
        this.error = error;
    }

    public ErrorResponseDto(Exception e) {
        this(e, e.getMessage());
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getException() {
        return exception;
    }

    public String getError() {
        return error;
    }
}
