package app.ddd.gsandwiches.core.domain;

/**
 * A value object, as described in the DDD book.
 * 
 * <p>
 * Value Objects are immutable and defined solely by their attributes.
 * They do not have a unique identity and should be compared based on their
 * values.
 * Therefore, implementations <strong>must</strong> override
 * {@link #equals(Object)} and {@link #hashCode()} to ensure proper equality
 * checks.
 * </p>
 */
public interface ValueObject {

    /**
     * Creates a new instance of this Value Object with the same attributes.
     *
     * @return A safe, deep copy of this value object.
     */
    ValueObject copy();
}
