package app.ddd.gsandwiches.sandwich.domain;

import static app.ddd.gsandwiches.sandwich.domain.SandwichTestFixture.EXPECTED_DESCRIPTION;
import static app.ddd.gsandwiches.sandwich.domain.SandwichTestFixture.EXPECTED_NAME;
import static app.ddd.gsandwiches.sandwich.domain.SandwichTestFixture.EXPECTED_PRICE;
import static app.ddd.gsandwiches.sandwich.domain.SandwichTestFixture.EXPECTED_SANDWICH;
import static app.ddd.gsandwiches.sandwich.domain.SandwichTestFixture.EXPECTED_SANDWICH_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

public class SandwichTest {

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

    @Test
    void testEqualsWithSameSandwich() {
        assertTrue(EXPECTED_SANDWICH.equals(EXPECTED_SANDWICH), "Sandwich should be equal to itself");
    }

    @Test
    void testEqualsWithNull() {
        assertFalse(EXPECTED_SANDWICH.equals(null), "Sandwich shouldn't equal null");
    }

    @Test
    void testEqualsWithDifferentSandwich() {
        var mockEntity = mock(EXPECTED_SANDWICH.getClass());
        assertFalse(EXPECTED_SANDWICH.equals(mockEntity), "Sandwich shouldn't be equal to a different sandwich");
    }

    @Test
    void testEqualsWithEqualSandwich() {
        var other = new Sandwich(EXPECTED_SANDWICH_ID, EXPECTED_NAME, EXPECTED_PRICE, EXPECTED_DESCRIPTION);
        assertTrue(EXPECTED_SANDWICH.equals(other), "Sandwich should be equal to a sandwich with the same ID");
    }
}
