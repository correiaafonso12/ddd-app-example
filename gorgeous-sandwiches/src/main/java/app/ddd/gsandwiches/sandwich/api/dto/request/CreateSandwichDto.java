package app.ddd.gsandwiches.sandwich.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateSandwichDto(
        @NotNull Long sandwichId,
        @NotBlank String name,
        @NotNull Double price,
        @NotBlank String description) {
}
