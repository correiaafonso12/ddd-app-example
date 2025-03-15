package app.ddd.gsandwiches.shared.domain;

import static org.apache.commons.lang3.Validate.notNull;

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
