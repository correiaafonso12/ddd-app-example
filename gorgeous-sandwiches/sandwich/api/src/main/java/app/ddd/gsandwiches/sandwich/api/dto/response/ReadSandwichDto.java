package app.ddd.gsandwiches.sandwich.api.dto.response;

import app.ddd.gsandwiches.sandwich.domain.Sandwich;

public class ReadSandwichDto {

    private final Long sandwichId;
    private final String name;
    private final Double price;
    private final String description;

    public ReadSandwichDto(Sandwich sandwich) {
        sandwichId = sandwich.id().value();
        name = sandwich.name().value();
        price = sandwich.price().value();
        description = sandwich.description().value();
    }

    public Long getSandwichId() {
        return sandwichId;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

}
