package app.ddd.gsandwiches.sandwich.domain.valueobjects;

import static app.ddd.gsandwiches.sandwich.domain.SandwichTestFixture.EXPECTED_DESCRIPTION;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import app.ddd.gsandwiches.shared.domain.ValueObjectTest;

public class DescriptionTest extends ValueObjectTest<Description> {

    @Override
    protected Description createInstance() {
        return EXPECTED_DESCRIPTION;
    }

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

}
