package app.ddd.gsandwiches.sandwich.persistence.mappers;

import static app.ddd.gsandwiches.sandwich.domain.SandwichTestFixture.EXPECTED_SANDWICH_ID;
import static app.ddd.gsandwiches.sandwich.persistence.SandwichSchemaTestFixture.EXPECTED_SANDWICH_SCHEMA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SandwichSchemaToSandwichMapperTest {

    private SandwichSchemaToSandwichMapper mapper;

    @BeforeEach
    public void setup() {
        mapper = new SandwichSchemaToSandwichMapper();
    }

    @Test
    void testMapWithNullSchema() {
        assertThrows(
                NullPointerException.class,
                () -> mapper.map(null),
                "Map to sandwich with null schema should throw");
    }

    @Test
    void testMapWithValidSchema() {
        var sandwich = mapper.map(EXPECTED_SANDWICH_SCHEMA);
        assertEquals(EXPECTED_SANDWICH_ID.value(), sandwich.id().value(), "Sandwich and schema should match");
    }
}
