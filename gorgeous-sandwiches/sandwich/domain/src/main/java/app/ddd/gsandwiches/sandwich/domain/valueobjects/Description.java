package app.ddd.gsandwiches.sandwich.domain.valueobjects;

import static org.apache.commons.lang3.Validate.notBlank;

import app.ddd.gsandwiches.core.spi.domain.ValueObject;

public record Description(String value) implements ValueObject {

    public Description {
        notBlank(value, "Description must have a value");
    }

    @Override
    public Description copy() {
        return new Description(value);
    }

}
