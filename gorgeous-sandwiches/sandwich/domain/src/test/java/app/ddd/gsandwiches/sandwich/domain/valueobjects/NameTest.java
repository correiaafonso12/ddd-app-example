package app.ddd.gsandwiches.sandwich.domain.valueobjects;

import static app.ddd.gsandwiches.sandwich.domain.SandwichTestFixture.EXPECTED_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class NameTest {

    @Test
    void testCreateNullName() {
        assertThrows(
                NullPointerException.class,
                () -> new Name(null),
                "Null Name must throw an exception");
    }

    @Test
    void testCreateEmptyName() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Name(""),
                "Empty Name must throw an exception");
    }

    @Test
    void testCreateBlankName() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Name("   "),
                "Blank Name must throw an exception");
    }

    @Test
    void testCreateValidName() {
        var value = "smt";
        var name = new Name(value);
        assertEquals(value, name.value(), "Name must be inputted value");
    }

    @Test
    void testCopy() {
        var copy = EXPECTED_NAME.copy();
        assertTrue(EXPECTED_NAME != copy, "Copy should not be original object");
        assertTrue(EXPECTED_NAME.equals(copy), "Copy should equal original object");
    }

}
