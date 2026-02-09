package app.ddd.gsandwiches.sandwich.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import app.ddd.gsandwiches.common.mapper.registry.MapperRegistry;
import app.ddd.gsandwiches.sandwich.api.mappers.CreateSandwichDtoToSandwichMapper;
import jakarta.annotation.PostConstruct;

@AutoConfiguration
@ComponentScan
public class SandwichApiExtension {

    @Autowired
    private MapperRegistry mapperRegistry;

    @PostConstruct
    private void initialize() {
        mapperRegistry.register(new CreateSandwichDtoToSandwichMapper());
    }

}
