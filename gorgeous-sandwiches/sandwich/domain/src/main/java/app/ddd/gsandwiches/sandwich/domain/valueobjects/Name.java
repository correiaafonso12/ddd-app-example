package app.ddd.gsandwiches.sandwich.domain.valueobjects;

import static org.apache.commons.lang3.Validate.notBlank;

import app.ddd.gsandwiches.core.domain.ValueObject;

public record Name(String value) implements ValueObject {

    public Name {
        notBlank(value, "Name must have a value");
    }

    @Override
    public Name copy() {
        return new Name(value);
    }

}
