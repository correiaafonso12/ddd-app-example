package app.ddd.gsandwiches.sandwich.persistence.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import app.ddd.gsandwiches.sandwich.persistence.schema.SandwichSchema;

public interface SandwichRepository extends JpaRepository<SandwichSchema, Long> {

    Optional<SandwichSchema> findBySandwichId(Long id);

    Optional<SandwichSchema> findByName(String name);
}
