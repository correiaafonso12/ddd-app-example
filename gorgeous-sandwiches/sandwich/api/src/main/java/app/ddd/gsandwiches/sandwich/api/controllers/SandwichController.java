package app.ddd.gsandwiches.sandwich.api.controllers;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMethodCall;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import app.ddd.gsandwiches.sandwich.api.SandwichApi;
import app.ddd.gsandwiches.sandwich.api.dto.request.CreateSandwichDto;
import app.ddd.gsandwiches.sandwich.api.dto.response.ReadSandwichDto;
import app.ddd.gsandwiches.sandwich.application.SandwichService;
import app.ddd.gsandwiches.sandwich.domain.Sandwich;
import app.ddd.gsandwiches.sandwich.domain.valueobjects.SandwichId;
import app.ddd.gsandwiches.common.exceptionhandler.ExceptionHandler;
import app.ddd.gsandwiches.common.mapper.registry.MapperRegistry;

@RestController
class SandwichController implements SandwichApi {

    private final SandwichService service;
    private final MapperRegistry mapperRegistry;
    private final ExceptionHandler exceptionHandler;

    public SandwichController(
            SandwichService service,
            MapperRegistry mapperRegistry,
            ExceptionHandler exceptionHandler) {
        this.service = service;
        this.mapperRegistry = mapperRegistry;
        this.exceptionHandler = exceptionHandler;
    }

    @Override
    public ResponseEntity<List<ReadSandwichDto>> getAll() {
        return ok(service.getAll().stream()
                .map(ReadSandwichDto::new)
                .toList());
    }

    @Override
    public ResponseEntity<ReadSandwichDto> getById(Long id) {
        return ResponseEntity.of(
                service.getById(new SandwichId(id))
                        .map(ReadSandwichDto::new));
    }

    @Override
    public ResponseEntity<?> create(CreateSandwichDto dto) {
        var sandwich = mapperRegistry.findAndMap(dto, Sandwich.class);

        return service.create(sandwich).fold(
                v -> handleCreated(dto.sandwichId()),
                exceptionHandler::handle);
    }

    private ResponseEntity<Object> handleCreated(Long id) {
        var location = fromMethodCall(on(SandwichController.class).getById(id)).build();
        return created(location.toUri()).build();
    }

}
