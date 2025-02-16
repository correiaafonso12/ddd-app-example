package app.ddd.gsandwiches.sandwich.domain;

import app.ddd.gsandwiches.sandwich.domain.valueobjects.Description;
import app.ddd.gsandwiches.sandwich.domain.valueobjects.Name;
import app.ddd.gsandwiches.sandwich.domain.valueobjects.Price;
import app.ddd.gsandwiches.sandwich.domain.valueobjects.SandwichId;

public class SandwichTestFixture {

    public static final SandwichId EXPECTED_SANDWICH_ID = new SandwichId(1L);
    public static final Name EXPECTED_NAME = new Name("smt");
    public static final Price EXPECTED_PRICE = new Price(10.0);
    public static final Description EXPECTED_DESCRIPTION = new Description("smt");

    public static final Sandwich EXPECTED_SANDWICH = new Sandwich(EXPECTED_SANDWICH_ID, EXPECTED_NAME, EXPECTED_PRICE, EXPECTED_DESCRIPTION);
}
