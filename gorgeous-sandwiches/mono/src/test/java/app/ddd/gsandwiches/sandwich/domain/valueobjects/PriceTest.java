package app.ddd.gsandwiches.sandwich.domain.valueobjects;

import static app.ddd.gsandwiches.sandwich.domain.SandwichTestFixture.EXPECTED_PRICE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PriceTest {

    @Test
    void testCreateNullPrice() {
        assertThrows(
                NullPointerException.class,
                () -> new Price(null),
                "Null Price must throw an exception");
    }

    @Test
    void testCreatePriceBelowLowerBound() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Price(-1.0),
                "Price below lower bound must throw an exception");
    }

    @Test
    void testCreatePriceBeyondUpperBound() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Price(Double.MAX_VALUE),
                "Price above upper bound must throw an exception");
    }

    @Test
    void testCreateValidPrice() {
        var value = 10.0;
        var price = new Price(value);
        assertEquals(value, price.value(), "Price must be inputted value");
    }

    @Test
    void testCopy() {
        var copy = EXPECTED_PRICE.copy();
        assertTrue(EXPECTED_PRICE != copy, "Copy should not be original object");
        assertTrue(EXPECTED_PRICE.equals(copy), "Copy should equal original object");
    }

}
