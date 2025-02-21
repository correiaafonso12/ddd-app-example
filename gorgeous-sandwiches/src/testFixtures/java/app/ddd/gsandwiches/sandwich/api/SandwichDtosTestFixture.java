package app.ddd.gsandwiches.sandwich.api;

import static app.ddd.gsandwiches.sandwich.domain.SandwichTestFixture.EXPECTED_DESCRIPTION;
import static app.ddd.gsandwiches.sandwich.domain.SandwichTestFixture.EXPECTED_NAME;
import static app.ddd.gsandwiches.sandwich.domain.SandwichTestFixture.EXPECTED_PRICE;
import static app.ddd.gsandwiches.sandwich.domain.SandwichTestFixture.EXPECTED_SANDWICH_ID;

import app.ddd.gsandwiches.sandwich.api.dto.request.CreateSandwichDto;

public class SandwichDtosTestFixture {

    public static final CreateSandwichDto EXPECTED_CREATE_SANDWICH_DTO = new CreateSandwichDto(
            EXPECTED_SANDWICH_ID.value(),
            EXPECTED_NAME.value(),
            EXPECTED_PRICE.value(),
            EXPECTED_DESCRIPTION.value());
}
