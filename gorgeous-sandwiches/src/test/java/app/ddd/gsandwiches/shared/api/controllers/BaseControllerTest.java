package app.ddd.gsandwiches.shared.api.controllers;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatusCode;

import app.ddd.gsandwiches.shared.application.exceptions.ApplicationException;
import app.ddd.gsandwiches.shared.application.exceptions.ConflictException;

public abstract class BaseControllerTest<T extends BaseController> {

    private T instance;

    protected abstract T createInstance();

    @BeforeEach
    public void setup() {
        instance = createInstance();
    }

    @Test
    void testHandleExceptionWithConflictException() {
        var exception = mock(ConflictException.class);
        when(exception.getMessage()).thenReturn("smt");
        var response = instance.handleException(exception);
        assertTrue(
                response.getStatusCode().isSameCodeAs(HttpStatusCode.valueOf(CONFLICT.value())),
                "Response should contain Conflict status code");
    }

    @Test
    void testHandleExceptionWithBadRequestException() {
        var exception = mock(ApplicationException.class);
        when(exception.getMessage()).thenReturn("smt");
        var response = instance.handleException(exception);
        assertTrue(
                response.getStatusCode().isSameCodeAs(HttpStatusCode.valueOf(BAD_REQUEST.value())),
                "Response should contain Bad Request status code");
    }

    @Test
    void testHandleExceptionWithInternalServerErrorException() {
        var exception = mock(RuntimeException.class);
        when(exception.getMessage()).thenReturn("smt");
        var response = instance.handleException(exception);
        assertTrue(
                response.getStatusCode().isSameCodeAs(HttpStatusCode.valueOf(INTERNAL_SERVER_ERROR.value())),
                "Response should contain Bad Request status code");
    }

}
