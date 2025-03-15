package app.ddd.gsandwiches.sandwich.domain.valueobjects;

import static app.ddd.gsandwiches.sandwich.domain.SandwichTestFixture.EXPECTED_SANDWICH_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import app.ddd.gsandwiches.shared.domain.ValueObjectTest;

public class SandwichIdTest extends ValueObjectTest<SandwichId> {

    @Override
    protected SandwichId createInstance() {
        return EXPECTED_SANDWICH_ID;
    }

    @Test
    void testCreateNullSandwichId() {
        assertThrows(
                NullPointerException.class,
                () -> new SandwichId(null),
                "Null ID must throw an exception");
    }

    @Test
    void testCreateSandwichIdBelowLowerBound() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new SandwichId(-1L),
                "ID below lower bound must throw an exception");
    }

    @Test
    void testCreateValidSandwichId() {
        var value = 1L;
        var id = new SandwichId(value);
        assertEquals(value, id.value(), "ID must be inputted value");
    }
}
