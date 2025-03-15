package app.ddd.gsandwiches.sandwich.domain.valueobjects;

import static app.ddd.gsandwiches.sandwich.domain.SandwichTestFixture.EXPECTED_PRICE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import app.ddd.gsandwiches.shared.domain.ValueObjectTest;

public class PriceTest extends ValueObjectTest<Price> {

    @Override
    protected Price createInstance() {
        return EXPECTED_PRICE;
    }

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

}
