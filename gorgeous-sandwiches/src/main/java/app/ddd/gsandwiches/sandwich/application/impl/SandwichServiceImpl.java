package app.ddd.gsandwiches.sandwich.application.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import app.ddd.gsandwiches.sandwich.application.SandwichService;
import app.ddd.gsandwiches.sandwich.application.exceptions.SandwichWithNonUniqueIdException;
import app.ddd.gsandwiches.sandwich.application.exceptions.SandwichWithNonUniqueNameException;
import app.ddd.gsandwiches.sandwich.domain.Sandwich;
import app.ddd.gsandwiches.sandwich.domain.dao.SandwichDao;
import app.ddd.gsandwiches.sandwich.domain.valueobjects.SandwichId;
import app.ddd.gsandwiches.shared.application.result.EmptyResult;
import app.ddd.gsandwiches.shared.application.result.Result;

@Service
class SandwichServiceImpl implements SandwichService {

    private final SandwichDao dao;

    public SandwichServiceImpl(SandwichDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Sandwich> getAll() {
        return dao.getAll();
    }

    @Override
    public Optional<Sandwich> getById(SandwichId id) {
        return dao.getById(id);
    }

    @Override
    public EmptyResult create(Sandwich sandwich) {
        return Result.empty()
                .verify(() -> getById(sandwich.id()).isEmpty(), SandwichWithNonUniqueIdException::new)
                .verify(() -> !dao.existsByName(sandwich.name()), SandwichWithNonUniqueNameException::new)
                .onSuccess(() -> dao.save(sandwich));
    }

}
