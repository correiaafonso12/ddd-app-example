package app.ddd.gsandwiches.sandwich.application.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import app.ddd.gsandwiches.result.Result;
import app.ddd.gsandwiches.sandwich.application.SandwichService;
import app.ddd.gsandwiches.sandwich.application.exceptions.SandwichWithNonUniqueIdException;
import app.ddd.gsandwiches.sandwich.application.exceptions.SandwichWithNonUniqueNameException;
import app.ddd.gsandwiches.sandwich.domain.Sandwich;
import app.ddd.gsandwiches.sandwich.domain.dao.SandwichDao;
import app.ddd.gsandwiches.sandwich.domain.valueobjects.SandwichId;

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
    public Result<Void> create(Sandwich sandwich) {
        return Result.success()
                .verify(v -> getById(sandwich.id()).isEmpty(), SandwichWithNonUniqueIdException::new)
                .verify(v -> !dao.existsByName(sandwich.name()), SandwichWithNonUniqueNameException::new)
                .onSuccess(v -> dao.save(sandwich));
    }

}
