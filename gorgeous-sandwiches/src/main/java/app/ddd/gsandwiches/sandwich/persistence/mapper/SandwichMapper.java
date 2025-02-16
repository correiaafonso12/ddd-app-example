package app.ddd.gsandwiches.sandwich.persistence.mapper;

import org.springframework.stereotype.Service;

import app.ddd.gsandwiches.sandwich.domain.Sandwich;
import app.ddd.gsandwiches.sandwich.domain.valueobjects.Description;
import app.ddd.gsandwiches.sandwich.domain.valueobjects.Name;
import app.ddd.gsandwiches.sandwich.domain.valueobjects.Price;
import app.ddd.gsandwiches.sandwich.domain.valueobjects.SandwichId;
import app.ddd.gsandwiches.sandwich.persistence.schema.SandwichSchema;
import app.ddd.gsandwiches.shared.persistence.Mapper;

@Service
class SandwichMapper implements Mapper<Sandwich, SandwichSchema> {

    @Override
    public SandwichSchema toSchema(Sandwich domain) {
        return new SandwichSchema(
                domain.id().value(),
                domain.name().value(),
                domain.price().value(),
                domain.description().value());
    }

    @Override
    public Sandwich toDomain(SandwichSchema schema) {
        return new Sandwich(
                new SandwichId(schema.sandwichId()),
                new Name(schema.name()),
                new Price(schema.price()),
                new Description(schema.description()));
    }

}
