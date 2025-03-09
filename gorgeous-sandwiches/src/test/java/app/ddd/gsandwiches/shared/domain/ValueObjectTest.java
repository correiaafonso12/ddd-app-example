package app.ddd.gsandwiches.shared.domain;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public abstract class ValueObjectTest<T extends ValueObject> {

    private T instance;

    protected abstract T createInstance();

    @BeforeEach
    public void setup() {
        instance = createInstance();
    }

    @Test
    void testEqualsWithSameValueObject() {
        assertTrue(instance.equals(instance), "Value object should be equal to itself");
    }

    @Test
    void testEqualsWithNull() {
        assertFalse(instance.equals(null), "Value object shouldn't equal null");
    }

    @Test
    void testEqualsWithOtherValueObject() {
        var mockValueObject = mock(ValueObject.class);
        assertFalse(
                instance.equals(mockValueObject),
                "Value object shouldn't be equal to other value object's implementations");
    }

    @Test
    void testEqualsWithDifferentValueObject() {
        var mockValueObject = mock(instance.getClass());
        assertFalse(instance.equals(mockValueObject), "Value object shouldn't be equal to a different value object");
    }

    @Test
    void testEqualsWithEqualsValueObject() {
        var other = createInstance();
        assertTrue(instance.equals(other), "Value object should be equal to a value object with the same ID");
    }

    @Test
    void testCopy() {
        var copy = instance.copy();
        assertTrue(instance != copy, "Copied object must not be the same as original object");
        assertTrue(instance.equals(copy), "Copied object value must be the same as original value");
    }
}
