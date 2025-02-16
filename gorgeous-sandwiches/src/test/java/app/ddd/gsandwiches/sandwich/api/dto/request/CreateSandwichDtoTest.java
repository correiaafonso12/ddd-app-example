package app.ddd.gsandwiches.sandwich.api.dto.request;

import static app.ddd.gsandwiches.sandwich.domain.SandwichTestFixture.EXPECTED_DESCRIPTION;
import static app.ddd.gsandwiches.sandwich.domain.SandwichTestFixture.EXPECTED_NAME;
import static app.ddd.gsandwiches.sandwich.domain.SandwichTestFixture.EXPECTED_PRICE;
import static app.ddd.gsandwiches.sandwich.domain.SandwichTestFixture.EXPECTED_SANDWICH_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CreateSandwichDtoTest {

    @Test
    void testCreate() {
        var dto = new CreateSandwichDto(EXPECTED_SANDWICH_ID.value(), EXPECTED_NAME.value(), EXPECTED_PRICE.value(), EXPECTED_DESCRIPTION.value());
        assertEquals(EXPECTED_SANDWICH_ID.value(), dto.sandwichId());
        assertEquals(EXPECTED_NAME.value(), dto.name());
        assertEquals(EXPECTED_PRICE.value(), dto.price());
        assertEquals(EXPECTED_DESCRIPTION.value(), dto.description());
    }
}
