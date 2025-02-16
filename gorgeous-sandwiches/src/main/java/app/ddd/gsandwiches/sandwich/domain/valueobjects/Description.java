package app.ddd.gsandwiches.sandwich.domain.valueobjects;

import static org.apache.commons.lang3.Validate.notBlank;

import app.ddd.gsandwiches.shared.domain.ValueObject;

public record Description(String value) implements ValueObject {

    public Description {
        notBlank(value, "Description must have a value");
    }

    @Override
    public boolean sameValueAs(ValueObject other) {
        return other instanceof Description d && value.equals(d.value);
    }

    @Override
    public Description copy() {
        return new Description(value);
    }

}
