package app.ddd.gsandwiches.sandwich.domain.valueobjects;

import static org.apache.commons.lang3.Validate.isTrue;
import static org.apache.commons.lang3.Validate.notNull;

import app.ddd.gsandwiches.core.domain.ValueObject;

public record SandwichId(Long value) implements ValueObject {

    private static final Long LOWER_BOUND = 1L;

    public SandwichId {
        notNull(value, "Sandwich ID must not be null");
        isTrue(value >= LOWER_BOUND, "Sandwich ID not in range");
    }

    @Override
    public SandwichId copy() {
        return new SandwichId(value);
    }
}
