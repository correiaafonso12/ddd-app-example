package app.ddd.gsandwiches.sandwich.application.exceptions;

import app.ddd.gsandwiches.core.exceptions.ConflictException;

public class SandwichWithNonUniqueNameException extends ConflictException {

    public SandwichWithNonUniqueNameException() {
        super("Sandwich name must be unique");
    }
}
