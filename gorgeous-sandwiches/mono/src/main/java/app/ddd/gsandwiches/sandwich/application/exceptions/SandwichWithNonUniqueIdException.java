package app.ddd.gsandwiches.sandwich.application.exceptions;

import app.ddd.gsandwiches.shared.application.exceptions.ConflictException;

public class SandwichWithNonUniqueIdException extends ConflictException {

    public SandwichWithNonUniqueIdException() {
        super("Sandwich Id must be unique");
    }

}
