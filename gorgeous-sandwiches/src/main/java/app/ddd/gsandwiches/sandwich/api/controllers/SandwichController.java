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
import app.ddd.gsandwiches.sandwich.domain.valueobjects.Description;
import app.ddd.gsandwiches.sandwich.domain.valueobjects.Name;
import app.ddd.gsandwiches.sandwich.domain.valueobjects.Price;
import app.ddd.gsandwiches.sandwich.domain.valueobjects.SandwichId;
import app.ddd.gsandwiches.shared.api.controllers.BaseController;

@RestController
class SandwichController extends BaseController implements SandwichApi {

    private final SandwichService service;

    public SandwichController(SandwichService service) {
        this.service = service;
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
        var sandwich = new Sandwich(
                new SandwichId(dto.sandwichId()),
                new Name(dto.name()),
                new Price(dto.price()),
                new Description(dto.description()));

        return service.create(sandwich).fold(
                () -> handleCreated(dto.sandwichId()),
                this::handleException);
    }

    private ResponseEntity<Object> handleCreated(Long id) {
        return created(
                fromMethodCall(on(SandwichController.class).getById(id))
                        .build()
                        .toUri())
                .build();
    }

}
