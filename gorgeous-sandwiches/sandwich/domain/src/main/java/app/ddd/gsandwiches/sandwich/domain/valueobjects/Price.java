package app.ddd.gsandwiches.sandwich.domain.valueobjects;

import static org.apache.commons.lang3.Validate.inclusiveBetween;
import static org.apache.commons.lang3.Validate.notNull;

import app.ddd.gsandwiches.core.spi.domain.ValueObject;

public record Price(Double value) implements ValueObject {

    private static final Double LOWER_BOUND = 0.0;
    private static final Double UPPER_BOUND = 10000.0;

    public Price {
        notNull(value, "Price must have a value");
        inclusiveBetween(LOWER_BOUND, UPPER_BOUND, value, "Price not in range");
    }

    @Override
    public Price copy() {
        return new Price(value);
    }

}
