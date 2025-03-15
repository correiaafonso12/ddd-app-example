package app.ddd.gsandwiches.sandwich.persistence.mappers;

import app.ddd.gsandwiches.sandwich.domain.Sandwich;
import app.ddd.gsandwiches.sandwich.domain.valueobjects.Description;
import app.ddd.gsandwiches.sandwich.domain.valueobjects.Name;
import app.ddd.gsandwiches.sandwich.domain.valueobjects.Price;
import app.ddd.gsandwiches.sandwich.domain.valueobjects.SandwichId;
import app.ddd.gsandwiches.sandwich.persistence.schema.SandwichSchema;
import app.ddd.gsandwiches.common.mapper.Mapper;

public class SandwichSchemaToSandwichMapper implements Mapper<SandwichSchema, Sandwich> {

    @Override
    public Class<SandwichSchema> sourceType() {
        return SandwichSchema.class;
    }

    @Override
    public Class<Sandwich> targetType() {
        return Sandwich.class;
    }

    @Override
    public Sandwich map(SandwichSchema source) {
        return new Sandwich(
                new SandwichId(source.sandwichId()),
                new Name(source.name()),
                new Price(source.price()),
                new Description(source.description()));
    }

}
