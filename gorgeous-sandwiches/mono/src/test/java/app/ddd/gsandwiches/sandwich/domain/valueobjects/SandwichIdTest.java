package app.ddd.gsandwiches.sandwich.domain.valueobjects;

import static app.ddd.gsandwiches.sandwich.domain.SandwichTestFixture.EXPECTED_SANDWICH_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SandwichIdTest {

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

    @Test
    void testCopy() {
        var copy = EXPECTED_SANDWICH_ID.copy();
        assertTrue(EXPECTED_SANDWICH_ID != copy, "Copy should not be original object");
        assertTrue(EXPECTED_SANDWICH_ID.equals(copy), "Copy should equal original object");
    }
}
