package app.ddd.gsandwiches.sandwich.api.controllers;

import static app.ddd.gsandwiches.sandwich.api.SandwichDtosTestFixture.EXPECTED_CREATE_SANDWICH_DTO;
import static app.ddd.gsandwiches.sandwich.domain.SandwichTestFixture.EXPECTED_SANDWICH;
import static app.ddd.gsandwiches.sandwich.domain.SandwichTestFixture.EXPECTED_SANDWICH_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.badRequest;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatusCode;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import app.ddd.gsandwiches.sandwich.application.SandwichService;
import app.ddd.gsandwiches.sandwich.domain.Sandwich;
import app.ddd.gsandwiches.shared.api.dto.response.ErrorResponseDto;
import app.ddd.gsandwiches.shared.api.handlers.ExceptionHandler;
import app.ddd.gsandwiches.shared.application.result.Result;
import app.ddd.gsandwiches.shared.mapper.registry.MapperRegistry;

public class SandwichControllerTest {

    private SandwichService serviceMock;
    private MapperRegistry mapperRegistryMock;
    private ExceptionHandler exceptionHandlerMock;
    private SandwichController controller;

    @BeforeEach
    public void init() {
        serviceMock = mock(SandwichService.class);
        mapperRegistryMock = mock(MapperRegistry.class);
        exceptionHandlerMock = mock(ExceptionHandler.class);
        controller = new SandwichController(serviceMock, mapperRegistryMock, exceptionHandlerMock);
    }

    @Test
    void testGetAll() {
        when(serviceMock.getAll()).thenReturn(List.of(EXPECTED_SANDWICH));
        var response = controller.getAll();
        assertTrue(
                response.getStatusCode().isSameCodeAs(HttpStatusCode.valueOf(OK.value())),
                "Response should be OK");
        assertEquals(
                EXPECTED_SANDWICH_ID.value(),
                response.getBody().get(0).getSandwichId(),
                "Response body should have the sandwich of the system");
    }

    @Test
    void testGetByIdWithExistingSandwich() {
        when(serviceMock.getById(any())).thenReturn(Optional.of(EXPECTED_SANDWICH));
        var response = controller.getById(EXPECTED_SANDWICH_ID.value());
        assertTrue(
                response.getStatusCode().isSameCodeAs(HttpStatusCode.valueOf(OK.value())),
                "Response should be OK");
        assertEquals(
                EXPECTED_SANDWICH_ID.value(),
                response.getBody().getSandwichId(),
                "Response body should have the sandwich of the system");
    }

    @Test
    void testGetByIdWithNonExistingSandwich() {
        when(serviceMock.getById(any())).thenReturn(Optional.empty());
        var response = controller.getById(EXPECTED_SANDWICH_ID.value());
        assertTrue(
                response.getStatusCode().isSameCodeAs(HttpStatusCode.valueOf(NOT_FOUND.value())),
                "Response should be Not Found");
        assertFalse(response.hasBody(), "Should not have body");
    }

    @Test
    void testCreateFailed() {
        when(mapperRegistryMock.findAndMap(EXPECTED_CREATE_SANDWICH_DTO, Sandwich.class)).thenReturn(EXPECTED_SANDWICH);
        when(serviceMock.create(any())).thenReturn(Result.empty().verify(() -> false, Exception::new));
        when(exceptionHandlerMock.handle(any())).thenReturn(badRequest().body(new ErrorResponseDto(new Exception())));
        var response = controller.create(EXPECTED_CREATE_SANDWICH_DTO);
        assertFalse(
                response.getStatusCode().isSameCodeAs(HttpStatusCode.valueOf(CREATED.value())),
                "Response should not be Created");
        assertTrue(response.hasBody(), "Should have body with error details");
    }

    @Test
    void testCreateSuccessful() {
        when(mapperRegistryMock.findAndMap(EXPECTED_CREATE_SANDWICH_DTO, Sandwich.class)).thenReturn(EXPECTED_SANDWICH);
        when(serviceMock.create(any())).thenReturn(Result.empty());
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        var response = controller.create(EXPECTED_CREATE_SANDWICH_DTO);
        assertTrue(
                response.getStatusCode().isSameCodeAs(HttpStatusCode.valueOf(CREATED.value())),
                "Response should be Created");
        assertFalse(response.hasBody(), "Should not have body");
    }

}
