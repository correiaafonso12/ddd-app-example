package app.ddd.gsandwiches.sandwich.persistence.mappers;

import static app.ddd.gsandwiches.sandwich.domain.SandwichTestFixture.EXPECTED_SANDWICH;
import static app.ddd.gsandwiches.sandwich.domain.SandwichTestFixture.EXPECTED_SANDWICH_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SandwichToSandwichSchemaMapperTest {

    private SandwichToSandwichSchemaMapper mapper;

    @BeforeEach
    public void setup() {
        mapper = new SandwichToSandwichSchemaMapper();
    }

    @Test
    void testMapWithNullSandwich() {
        assertThrows(
                NullPointerException.class,
                () -> mapper.map(null),
                "Map to schema with null sandwich should throw");
    }

    @Test
    void testMapWithValidSandwich() {
        var schema = mapper.map(EXPECTED_SANDWICH);
        assertEquals(EXPECTED_SANDWICH_ID.value(), schema.sandwichId(), "Sandwich and schema should match");
    }

}
