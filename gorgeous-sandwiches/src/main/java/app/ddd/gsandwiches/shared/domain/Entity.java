package app.ddd.gsandwiches.shared.domain;

import static org.apache.commons.lang3.Validate.notNull;

import java.util.Optional;

/**
 * An entity, as explained in the DDD book.
 */
public abstract class Entity<ID extends ValueObject> {

    /**
     * Entities must define an immutable identity
     */
    private ID id;

    protected Entity(ID id) {
        notNull(id, "Entities must have an ID");
        this.id = id;
    }

    public ID id() {
        return id;
    }

    /**
     * Entities compare by identity, not by attributes.
     *
     * @param other The other entity.
     * @return true if the identities are the same, regardless of other attributes.
     */
    public boolean sameIdentityAs(Entity<ID> other) {
        return this == other ||
                Optional.ofNullable(other)
                        .filter(o -> o.getClass() == getClass())
                        .filter(o -> o.id.sameValueAs(id))
                        .isPresent();
    }

}
