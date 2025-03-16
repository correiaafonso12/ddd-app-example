package app.ddd.gsandwiches.sandwich.domain.valueobjects;

import static app.ddd.gsandwiches.sandwich.domain.SandwichTestFixture.EXPECTED_DESCRIPTION;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DescriptionTest {

    @Test
    void testCreateNullDescription() {
        assertThrows(
                NullPointerException.class,
                () -> new Description(null),
                "Null Description must throw an exception");
    }

    @Test
    void testCreateEmptyDescription() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Description(""),
                "Empty Description must throw an exception");
    }

    @Test
    void testCreateBlankDescription() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Description("   "),
                "Blank Description must throw an exception");
    }

    @Test
    void testCreateValidDescription() {
        var value = "smt";
        var description = new Description(value);
        assertEquals(value, description.value(), "Description must be inputted value");
    }

    @Test
    void testCopy() {
        var copy = EXPECTED_DESCRIPTION.copy();
        assertTrue(EXPECTED_DESCRIPTION != copy, "Copy should not be original object");
        assertTrue(EXPECTED_DESCRIPTION.equals(copy), "Copy should equal original object");
    }

}
