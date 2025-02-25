package app.ddd.gsandwiches.config;

import org.springframework.stereotype.Component;

import app.ddd.gsandwiches.sandwich.persistence.mappers.SandwichSchemaToSandwichMapper;
import app.ddd.gsandwiches.sandwich.persistence.mappers.SandwichToSandwichSchemaMapper;
import app.ddd.gsandwiches.shared.config.Initializer;
import app.ddd.gsandwiches.shared.mapper.registry.MapperRegistry;

@Component
class MapperInitializer extends Initializer {

    private final MapperRegistry registry;

    public MapperInitializer(MapperRegistry registry) {
        this.registry = registry;
    }

    @Override
    public void initialize() {
        registry.register(new SandwichToSandwichSchemaMapper());
        registry.register(new SandwichSchemaToSandwichMapper());
    }

}
