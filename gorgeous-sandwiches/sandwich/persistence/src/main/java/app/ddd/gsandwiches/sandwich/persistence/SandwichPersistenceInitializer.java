package app.ddd.gsandwiches.sandwich.persistence;

import org.springframework.stereotype.Component;

import app.ddd.gsandwiches.common.initializer.Initializer;
import app.ddd.gsandwiches.common.mapper.registry.MapperRegistry;
import app.ddd.gsandwiches.sandwich.persistence.mappers.SandwichSchemaToSandwichMapper;
import app.ddd.gsandwiches.sandwich.persistence.mappers.SandwichToSandwichSchemaMapper;

@Component
class SandwichPersistenceInitializer extends Initializer {

    private final MapperRegistry mapperRegistry;

    public SandwichPersistenceInitializer(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }

    @Override
    protected void initialize() {
        mapperRegistry.register(new SandwichToSandwichSchemaMapper());
        mapperRegistry.register(new SandwichSchemaToSandwichMapper());
    }

}
