package app.ddd.gsandwiches.core.persistence;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

/**
 * Schema abstraction that automatically generates a primary key.
 * 
 * <p>
 * The database primary key and the domain entity id should not match, to avoid
 * exposing database information.
 * </p>
 */
@MappedSuperclass
public abstract class BaseSchema {

    // TODO: Enforce random positive long Id generation

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long primaryKey;

}
