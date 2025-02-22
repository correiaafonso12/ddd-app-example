package app.ddd.gsandwiches.sandwich.persistence.mappers;

import org.springframework.stereotype.Component;

import app.ddd.gsandwiches.sandwich.domain.Sandwich;
import app.ddd.gsandwiches.sandwich.domain.valueobjects.Description;
import app.ddd.gsandwiches.sandwich.domain.valueobjects.Name;
import app.ddd.gsandwiches.sandwich.domain.valueobjects.Price;
import app.ddd.gsandwiches.sandwich.domain.valueobjects.SandwichId;
import app.ddd.gsandwiches.sandwich.persistence.schema.SandwichSchema;
import app.ddd.gsandwiches.shared.mappers.BiDirectionalMapper;

@Component
class SandwichToSandwichSchemaMapper implements BiDirectionalMapper<Sandwich, SandwichSchema> {

  @Override
  public SandwichSchema map(Sandwich source) {
    return new SandwichSchema(
        source.id().value(),
        source.name().value(),
        source.price().value(),
        source.description().value());
  }

  @Override
  public Sandwich reverseMap(SandwichSchema target) {
    return new Sandwich(
        new SandwichId(target.sandwichId()),
        new Name(target.name()),
        new Price(target.price()),
        new Description(target.description()));
  }

}
