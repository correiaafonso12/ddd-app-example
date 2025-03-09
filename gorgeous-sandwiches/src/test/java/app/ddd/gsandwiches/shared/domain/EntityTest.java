package app.ddd.gsandwiches.shared.domain;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public abstract class EntityTest<T extends Entity<? extends ValueObject>> {

    private T instance;

    protected abstract T createInstance();

    @BeforeEach
    public void setup() {
        instance = createInstance();
    }

    @Test
    void testEqualsWithSameEntity() {
        assertTrue(instance.equals(instance), "Entity should be equal to itself");
    }

    @Test
    void testEqualsWithNull() {
        assertFalse(instance.equals(null), "Entity shouldn't equal null");
    }

    @Test
    void testEqualsWithOtherEntity() {
        var mockEntity = mock(Entity.class);
        assertFalse(instance.equals(mockEntity), "Entity shouldn't be equal to other entity implementations");
    }

    @Test
    void testEqualsWithDifferentEntity() {
        var mockEntity = mock(instance.getClass());
        assertFalse(instance.equals(mockEntity), "Entity shouldn't be equal to a different entity");
    }

    @Test
    void testEqualsWithEqualsEntity() {
        var other = createInstance();
        assertTrue(instance.equals(other), "Entity should be equal to an entity with the same ID");
    }
}
