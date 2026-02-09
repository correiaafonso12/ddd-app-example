package app.ddd.gsandwiches.sandwich.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import app.ddd.gsandwiches.common.mapper.registry.MapperRegistry;
import app.ddd.gsandwiches.sandwich.persistence.mappers.SandwichSchemaToSandwichMapper;
import app.ddd.gsandwiches.sandwich.persistence.mappers.SandwichToSandwichSchemaMapper;
import jakarta.annotation.PostConstruct;

@AutoConfiguration
@ComponentScan
@EnableJpaRepositories
@EntityScan
public class SandwichPersistenceExtension {

    @Autowired
    private MapperRegistry mapperRegistry;

    @PostConstruct
    private void initialize() {
        mapperRegistry.register(new SandwichToSandwichSchemaMapper());
        mapperRegistry.register(new SandwichSchemaToSandwichMapper());
    }

}
