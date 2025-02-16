package app.ddd.gsandwiches.sandwich.domain.valueobjects;

import static org.apache.commons.lang3.Validate.notBlank;

import app.ddd.gsandwiches.shared.domain.ValueObject;

public record Name(String value) implements ValueObject {

    public Name {
        notBlank(value, "Name must have a value");
    }

    @Override
    public boolean sameValueAs(ValueObject other) {
        return other instanceof Name n && value.equals(n.value);
    }

    @Override
    public Name copy() {
        return new Name(value);
    }

}
