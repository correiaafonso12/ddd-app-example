package app.ddd.gsandwiches.sandwich.domain.dao;

import java.util.List;
import java.util.Optional;

import app.ddd.gsandwiches.sandwich.domain.Sandwich;
import app.ddd.gsandwiches.sandwich.domain.valueobjects.Name;
import app.ddd.gsandwiches.sandwich.domain.valueobjects.SandwichId;

public interface SandwichDao {

    List<Sandwich> getAll();

    Optional<Sandwich> getById(SandwichId id);

    Optional<Sandwich> getByName(Name name);

    void save(Sandwich sandwich);
}
