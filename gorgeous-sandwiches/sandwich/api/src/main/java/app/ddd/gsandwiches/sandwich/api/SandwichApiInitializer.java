package app.ddd.gsandwiches.sandwich.api;

import org.springframework.stereotype.Component;

import app.ddd.gsandwiches.common.initializer.Initializer;
import app.ddd.gsandwiches.common.mapper.registry.MapperRegistry;
import app.ddd.gsandwiches.sandwich.api.mappers.CreateSandwichDtoToSandwichMapper;

@Component
class SandwichApiInitializer extends Initializer {

    private final MapperRegistry mapperRegistry;

    public SandwichApiInitializer(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }

    @Override
    protected void initialize() {
        mapperRegistry.register(new CreateSandwichDtoToSandwichMapper());
    }

}
