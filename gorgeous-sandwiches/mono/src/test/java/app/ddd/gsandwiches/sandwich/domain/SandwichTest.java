package app.ddd.gsandwiches.sandwich.domain;

import static app.ddd.gsandwiches.sandwich.domain.SandwichTestFixture.EXPECTED_DESCRIPTION;
import static app.ddd.gsandwiches.sandwich.domain.SandwichTestFixture.EXPECTED_NAME;
import static app.ddd.gsandwiches.sandwich.domain.SandwichTestFixture.EXPECTED_PRICE;
import static app.ddd.gsandwiches.sandwich.domain.SandwichTestFixture.EXPECTED_SANDWICH;
import static app.ddd.gsandwiches.sandwich.domain.SandwichTestFixture.EXPECTED_SANDWICH_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import app.ddd.gsandwiches.shared.domain.EntityTest;

public class SandwichTest extends EntityTest<Sandwich> {

    @Override
    protected Sandwich createInstance() {
        return EXPECTED_SANDWICH;
    }

    @Test
    void testCreateWithNullSandwichId() {
        assertThrows(
                NullPointerException.class,
                () -> new Sandwich(null, EXPECTED_NAME, EXPECTED_PRICE, EXPECTED_DESCRIPTION),
                "Null Sandwich ID must throw an exception");
    }

    @Test
    void testCreateWithNullName() {
        assertThrows(
                NullPointerException.class,
                () -> new Sandwich(EXPECTED_SANDWICH_ID, null, EXPECTED_PRICE, EXPECTED_DESCRIPTION),
                "Null Name must throw an exception");
    }

    @Test
    void testCreateWithNullPrice() {
        assertThrows(
                NullPointerException.class,
                () -> new Sandwich(EXPECTED_SANDWICH_ID, EXPECTED_NAME, null, EXPECTED_DESCRIPTION),
                "Null Price must throw an exception");
    }

    @Test
    void testCreateWithNullDescripttion() {
        assertThrows(
                NullPointerException.class,
                () -> new Sandwich(EXPECTED_SANDWICH_ID, EXPECTED_NAME, EXPECTED_PRICE, null),
                "Null Description must throw an exception");
    }

    @Test
    void testCreateValidSandwich() {
        var sandwich = new Sandwich(EXPECTED_SANDWICH_ID, EXPECTED_NAME, EXPECTED_PRICE, EXPECTED_DESCRIPTION);
        assertEquals(EXPECTED_SANDWICH_ID, sandwich.id(), "Sandwich ID must be inputted value");
        assertEquals(EXPECTED_NAME, sandwich.name(), "Name must be inputted value");
        assertEquals(EXPECTED_PRICE, sandwich.price(), "Price must be inputted value");
        assertEquals(EXPECTED_DESCRIPTION, sandwich.description(), "Description must be inputted value");
    }
}
