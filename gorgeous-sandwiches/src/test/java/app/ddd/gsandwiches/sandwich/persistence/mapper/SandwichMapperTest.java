package app.ddd.gsandwiches.sandwich.persistence.mapper;

import static app.ddd.gsandwiches.sandwich.domain.SandwichTestFixture.EXPECTED_SANDWICH;
import static app.ddd.gsandwiches.sandwich.domain.SandwichTestFixture.EXPECTED_SANDWICH_ID;
import static app.ddd.gsandwiches.sandwich.persistence.SandwichSchemaTestFixture.EXPECTED_SANDWICH_SCHEMA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SandwichMapperTest {

    private SandwichMapper mapper;

    @BeforeEach
    public void setup() {
        mapper = new SandwichMapper();
    }

    @Test
    void testToSchemaWithNullSandwich() {
        assertThrows(
                NullPointerException.class,
                () -> mapper.toSchema(null),
                "Map to schema with null sandwich should throw");
    }

    @Test
    void testToSchemaWithValidSandwich() {
        var schema = mapper.toSchema(EXPECTED_SANDWICH);
        assertEquals(EXPECTED_SANDWICH_ID.value(), schema.sandwichId(), "Sandwich and schema should match");
    }

    @Test
    void testToDomainWithNullSchema() {
        assertThrows(
                NullPointerException.class,
                () -> mapper.toDomain(null),
                "Map to schema with null sandwich should throw");
    }

    @Test
    void testToDomainWithValidSchema() {
        var sandwich = mapper.toDomain(EXPECTED_SANDWICH_SCHEMA);
        assertEquals(EXPECTED_SANDWICH_ID.value(), sandwich.id().value(), "Sandwich and schema should match");
    }

}
