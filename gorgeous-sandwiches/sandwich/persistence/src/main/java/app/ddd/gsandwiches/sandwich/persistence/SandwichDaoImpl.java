package app.ddd.gsandwiches.sandwich.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import app.ddd.gsandwiches.sandwich.domain.Sandwich;
import app.ddd.gsandwiches.sandwich.domain.dao.SandwichDao;
import app.ddd.gsandwiches.sandwich.domain.valueobjects.Name;
import app.ddd.gsandwiches.sandwich.domain.valueobjects.SandwichId;
import app.ddd.gsandwiches.sandwich.persistence.repositories.SandwichRepository;
import app.ddd.gsandwiches.sandwich.persistence.schema.SandwichSchema;
import app.ddd.gsandwiches.common.mapper.registry.MapperRegistry;

@Repository
class SandwichDaoImpl implements SandwichDao {

    private final SandwichRepository repository;
    private final MapperRegistry registry;

    public SandwichDaoImpl(SandwichRepository repository, MapperRegistry registry) {
        this.repository = repository;
        this.registry = registry;
    }

    @Override
    public List<Sandwich> getAll() {
        return repository.findAll().stream()
                .map(schema -> registry.findAndMap(schema, Sandwich.class))
                .toList();
    }

    @Override
    public Optional<Sandwich> getById(SandwichId id) {
        return repository.findBySandwichId(id.value())
                .map(schema -> registry.findAndMap(schema, Sandwich.class));
    }

    @Override
    public Optional<Sandwich> getByName(Name name) {
        return repository.findByName(name.value())
                .map(schema -> registry.findAndMap(schema, Sandwich.class));
    }

    @Override
    public void save(Sandwich sandwich) {
        var schema = registry.findAndMap(sandwich, SandwichSchema.class);
        repository.save(schema);
    }

}
