package app.ddd.gsandwiches.core.spi.domain;

import static java.util.Objects.requireNonNull;

/**
 * An entity, as explained in the DDD book.
 */
public abstract class Entity<ID extends ValueObject> {

    /**
     * Entities must define an immutable identity
     */
    private ID id;

    protected Entity(ID id) {
        this.id = requireNonNull(id, "Entities must have an ID");
    }

    public ID id() {
        return id;
    }

    /**
     * Entities compare by identity, not by attributes.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        var other = (Entity<?>) obj;
        return id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
