package app.ddd.gsandwiches.sandwich.persistence;

import static app.ddd.gsandwiches.sandwich.domain.SandwichTestFixture.EXPECTED_DESCRIPTION;
import static app.ddd.gsandwiches.sandwich.domain.SandwichTestFixture.EXPECTED_NAME;
import static app.ddd.gsandwiches.sandwich.domain.SandwichTestFixture.EXPECTED_PRICE;
import static app.ddd.gsandwiches.sandwich.domain.SandwichTestFixture.EXPECTED_SANDWICH_ID;

import app.ddd.gsandwiches.sandwich.persistence.schema.SandwichSchema;

public class SandwichSchemaTestFixture {

    public static final SandwichSchema EXPECTED_SANDWICH_SCHEMA = new SandwichSchema(
            EXPECTED_SANDWICH_ID.value(),
            EXPECTED_NAME.value(),
            EXPECTED_PRICE.value(),
            EXPECTED_DESCRIPTION.value());
}
