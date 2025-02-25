package app.ddd.gsandwiches.sandwich.persistence.mappers;

import app.ddd.gsandwiches.sandwich.domain.Sandwich;
import app.ddd.gsandwiches.sandwich.persistence.schema.SandwichSchema;
import app.ddd.gsandwiches.shared.mapper.Mapper;

public class SandwichToSandwichSchemaMapper implements Mapper<Sandwich, SandwichSchema> {

  @Override
  public Class<Sandwich> sourceType() {
    return Sandwich.class;
  }

  @Override
  public Class<SandwichSchema> targetType() {
    return SandwichSchema.class;
  }

  @Override
  public SandwichSchema map(Sandwich source) {
    return new SandwichSchema(
        source.id().value(),
        source.name().value(),
        source.price().value(),
        source.description().value());
  }

}
