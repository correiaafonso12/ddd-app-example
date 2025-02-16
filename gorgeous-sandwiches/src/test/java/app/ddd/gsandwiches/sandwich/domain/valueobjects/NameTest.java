package app.ddd.gsandwiches.sandwich.domain.valueobjects;

import static app.ddd.gsandwiches.sandwich.domain.SandwichTestFixture.EXPECTED_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import app.ddd.gsandwiches.shared.domain.ValueObjectTest;

public class NameTest extends ValueObjectTest<Name> {

    @Override
    protected Name createInstance() {
        return EXPECTED_NAME;
    }

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

}
