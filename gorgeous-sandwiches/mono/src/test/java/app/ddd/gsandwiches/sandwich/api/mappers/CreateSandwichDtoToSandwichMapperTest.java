package app.ddd.gsandwiches.sandwich.api.mappers;

import static app.ddd.gsandwiches.sandwich.api.SandwichDtosTestFixture.EXPECTED_CREATE_SANDWICH_DTO;
import static app.ddd.gsandwiches.sandwich.domain.SandwichTestFixture.EXPECTED_SANDWICH_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CreateSandwichDtoToSandwichMapperTest {

    private CreateSandwichDtoToSandwichMapper mapper;

    @BeforeEach
    public void setup() {
        mapper = new CreateSandwichDtoToSandwichMapper();
    }

    @Test
    void testMapWithNullSource() {
        assertThrows(
                NullPointerException.class,
                () -> mapper.map(null),
                "Map with null source should throw");
    }

    @Test
    void testMapWithValidSource() {
        var sandwich = mapper.map(EXPECTED_CREATE_SANDWICH_DTO);
        assertEquals(
                EXPECTED_SANDWICH_ID.value(), sandwich.id().value(),
                "Sandwich id and CreateSandwichDto id should match");
    }
}
