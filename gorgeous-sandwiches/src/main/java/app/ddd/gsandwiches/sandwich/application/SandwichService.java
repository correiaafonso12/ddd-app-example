package app.ddd.gsandwiches.sandwich.application;

import java.util.List;
import java.util.Optional;

import app.ddd.gsandwiches.sandwich.domain.Sandwich;
import app.ddd.gsandwiches.sandwich.domain.valueobjects.SandwichId;
import app.ddd.gsandwiches.shared.application.result.EmptyResult;

public interface SandwichService {

    List<Sandwich> getAll();

    Optional<Sandwich> getById(SandwichId id);

    EmptyResult create(Sandwich sandwich);
}
