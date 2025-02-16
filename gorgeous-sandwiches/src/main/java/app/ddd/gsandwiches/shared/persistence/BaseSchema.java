package app.ddd.gsandwiches.shared.persistence;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

/**
 * Schema abstraction that automatically generates a primary key
 */
@MappedSuperclass
public abstract class BaseSchema {

    /**
     * Primary Key
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long pk;

    public Long getPrimaryKey() {
        return pk;
    }

    @Override
    public int hashCode() {
        return getPrimaryKey() != null ? getPrimaryKey().hashCode() : 0;
    }
}
