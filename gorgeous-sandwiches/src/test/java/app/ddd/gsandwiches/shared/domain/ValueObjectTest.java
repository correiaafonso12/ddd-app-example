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
    void testSameValueAsWithNull() {
        assertFalse(instance.sameValueAs(null), "Value object shouldn't have the same value as null");
    }

    @Test
    void testSameValueAsWithOtherValueObject() {
        var mockValueObject = mock(ValueObject.class);
        assertFalse(
                instance.sameValueAs(mockValueObject),
                "Value object shouldn't compare with other value objects implementations");
    }

    @Test
    void testSameValueAsWithDifferentValueObject() {
        var mockInstance = mock(instance.getClass());
        assertFalse(
                instance.sameValueAs(mockInstance),
                "Value object shouldn't have the same value when compared with a different value object");
    }

    @Test
    void testSameValueAsWithSameObject() {
        assertTrue(instance.sameValueAs(instance), "Value object should have the same value when compared with itself");
    }

    @Test
    void testSameValueAsWithEqualValueObject() {
        var other = instance.copy();
        assertTrue(
                instance.sameValueAs(other),
                "Value object should have the same value when compared with an equal value object");
    }

    @Test
    void testCopy() {
        var copy = instance.copy();
        assertTrue(instance != copy, "Copied object must not be the same as original object");
        assertTrue(instance.sameValueAs(copy), "Copied object value must be the same as original value");
    }
}
