package app.ddd.gsandwiches.sandwich.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import app.ddd.gsandwiches.sandwich.api.dto.request.CreateSandwichDto;
import app.ddd.gsandwiches.sandwich.api.dto.response.ReadSandwichDto;
import app.ddd.gsandwiches.shared.api.dto.response.ErrorResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Sandwiches")
@RequestMapping("/api/sandwich")
public interface SandwichApi {

    @Operation(summary = "Get All Sandwiches", description = "Returns a list with all sandwiches")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(schema = @Schema(implementation = ReadSandwichDto[].class)) }) })
    @GetMapping
    public ResponseEntity<List<ReadSandwichDto>> getAll();

    @Operation(summary = "Get Sandwich by Id", description = "Returns a Sandwich with Given ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(schema = @Schema(implementation = ReadSandwichDto.class)) }),
            @ApiResponse(responseCode = "404", description = "Not Found") })
    @GetMapping(value = "/{id}")
    public ResponseEntity<ReadSandwichDto> getById(@PathVariable Long id);

    @Operation(summary = "Creates Sandwich", description = "Creates a new Sandwich")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                    @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            }),
            @ApiResponse(responseCode = "409", description = "Conflict", content = {
                    @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            }),
    })
    @PostMapping
    public ResponseEntity<?> create(@Validated @RequestBody CreateSandwichDto dto);

}
