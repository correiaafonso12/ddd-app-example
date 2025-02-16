package app.ddd.gsandwiches.shared.domain;

/**
 * A value object, as described in the DDD book.
 */
public interface ValueObject {

    /**
     * Value objects compare by the values of their attributes, they don't have an
     * identity.
     *
     * @param other The other value object.
     * @return <code>true</code> if the given value object's and this value object's
     *         attributes are the same.
     */
    public boolean sameValueAs(ValueObject other);

    /**
     * Value objects can be freely copied.
     *
     * @return A safe, deep copy of this value object.
     */
    public ValueObject copy();
}
