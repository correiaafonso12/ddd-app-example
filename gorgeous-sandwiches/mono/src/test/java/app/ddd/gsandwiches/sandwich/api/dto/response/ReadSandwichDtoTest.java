package app.ddd.gsandwiches.sandwich.api.dto.response;

import static app.ddd.gsandwiches.sandwich.domain.SandwichTestFixture.EXPECTED_DESCRIPTION;
import static app.ddd.gsandwiches.sandwich.domain.SandwichTestFixture.EXPECTED_NAME;
import static app.ddd.gsandwiches.sandwich.domain.SandwichTestFixture.EXPECTED_PRICE;
import static app.ddd.gsandwiches.sandwich.domain.SandwichTestFixture.EXPECTED_SANDWICH;
import static app.ddd.gsandwiches.sandwich.domain.SandwichTestFixture.EXPECTED_SANDWICH_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ReadSandwichDtoTest {

    @Test
    void testCreate() {
        var dto = new ReadSandwichDto(EXPECTED_SANDWICH);
        assertEquals(EXPECTED_SANDWICH_ID.value(), dto.getSandwichId());
        assertEquals(EXPECTED_NAME.value(), dto.getName());
        assertEquals(EXPECTED_PRICE.value(), dto.getPrice());
        assertEquals(EXPECTED_DESCRIPTION.value(), dto.getDescription());
    }
}
