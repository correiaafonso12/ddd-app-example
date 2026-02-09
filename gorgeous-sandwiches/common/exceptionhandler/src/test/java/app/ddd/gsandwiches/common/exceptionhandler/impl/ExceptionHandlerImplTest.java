package app.ddd.gsandwiches.common.exceptionhandler.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatusCode;

import app.ddd.gsandwiches.common.exceptionhandler.ExceptionHandler;
import app.ddd.gsandwiches.core.spi.exception.ApplicationException;
import app.ddd.gsandwiches.core.spi.exception.ConflictException;

public class ExceptionHandlerImplTest {

    private ExceptionHandler exceptionHandler;

    @BeforeEach
    public void setup() {
        exceptionHandler = new ExceptionHandlerImpl();
    }

    @Test
    void testHandleExceptionWithConflictException() {
        var response = exceptionHandler.handle(new ConflictException("smt"));
        assertTrue(
                response.getStatusCode().isSameCodeAs(HttpStatusCode.valueOf(CONFLICT.value())),
                "Response should contain Conflict status code");
    }

    @Test
    void testHandleExceptionWithBadRequestException() {
        var response = exceptionHandler.handle(new ApplicationException("smt"));
        assertTrue(
                response.getStatusCode().isSameCodeAs(HttpStatusCode.valueOf(BAD_REQUEST.value())),
                "Response should contain Bad Request status code");
    }

    @Test
    void testHandleExceptionWithInternalServerErrorException() {
        var response = exceptionHandler.handle(new RuntimeException("smt"));
        assertTrue(
                response.getStatusCode().isSameCodeAs(HttpStatusCode.valueOf(INTERNAL_SERVER_ERROR.value())),
                "Response should contain Internal Server Error status code");
    }

}
