package app.ddd.gsandwiches.sandwich.application;

import java.util.List;
import java.util.Optional;

import app.ddd.gsandwiches.result.Result;
import app.ddd.gsandwiches.sandwich.domain.Sandwich;
import app.ddd.gsandwiches.sandwich.domain.valueobjects.SandwichId;

public interface SandwichService {

    List<Sandwich> getAll();

    Optional<Sandwich> getById(SandwichId id);

    Result<Void> create(Sandwich sandwich);
}
