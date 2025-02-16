package app.ddd.gsandwiches.sandwich.domain;

import static org.apache.commons.lang3.Validate.notNull;

import app.ddd.gsandwiches.sandwich.domain.valueobjects.Description;
import app.ddd.gsandwiches.sandwich.domain.valueobjects.Name;
import app.ddd.gsandwiches.sandwich.domain.valueobjects.Price;
import app.ddd.gsandwiches.sandwich.domain.valueobjects.SandwichId;
import app.ddd.gsandwiches.shared.domain.Entity;

public class Sandwich extends Entity<SandwichId> {

    private Name name;
    private Price price;
    private Description description;

    public Sandwich(SandwichId id, Name name, Price price, Description description) {
        super(id);
        updateName(name);
        updatePrice(price);
        updateDescription(description);
    }

    public Name name() {
        return name;
    }

    public Price price() {
        return price;
    }

    public Description description() {
        return description;
    }

    private void updateName(Name name) {
        notNull(name, "Name must not be null");
        this.name = name;
    }

    private void updatePrice(Price price) {
        notNull(price, "Price must not be null");
        this.price = price;
    }

    private void updateDescription(Description description) {
        notNull(description, "Description must not be null");
        this.description = description;
    }
}
