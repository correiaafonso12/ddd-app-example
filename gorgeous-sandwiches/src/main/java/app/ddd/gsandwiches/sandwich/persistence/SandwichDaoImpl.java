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
import app.ddd.gsandwiches.shared.mappers.BiDirectionalMapper;

@Repository
class SandwichDaoImpl implements SandwichDao {

    private final SandwichRepository repository;
    private final BiDirectionalMapper<Sandwich, SandwichSchema> mapper;

    public SandwichDaoImpl(SandwichRepository repository, BiDirectionalMapper<Sandwich, SandwichSchema> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<Sandwich> getAll() {
        return repository.findAll().stream()
                .map(mapper::reverseMap)
                .toList();
    }

    @Override
    public Optional<Sandwich> getById(SandwichId id) {
        return repository.findBySandwichId(id.value())
                .map(mapper::reverseMap);
    }

    @Override
    public Boolean existsByName(Name name) {
        return repository.findByName(name.value())
                .isPresent();
    }

    @Override
    public void save(Sandwich sandwich) {
        var schema = mapper.map(sandwich);
        repository.save(schema);
    }

}
