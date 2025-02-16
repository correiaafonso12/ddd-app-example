package app.ddd.gsandwiches.shared.domain;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.ReflectionTestUtils.setField;

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
    void testSameIdentityAsWithNull() {
        assertFalse(instance.sameIdentityAs(null), "Entity shouldn't have the same identity as null");
    }

    @Test
    @SuppressWarnings("unchecked")
    void testSameIdentityAsWithOtherEntity() {
        var mockEntity = mock(Entity.class);
        assertFalse(
                instance.sameIdentityAs(mockEntity),
                "Entity shouldn't compare with other entity implementations");
    }

    @Test
    @SuppressWarnings("unchecked")
    void testSameIdentityAsWithDifferentEntity() {
        var mockInstance = mock(instance.getClass());
        var mockId = mock(ValueObject.class);
        when(mockId.sameValueAs(any())).thenReturn(false);
        setField(mockInstance, "id", mockId);

        assertFalse(
                instance.sameIdentityAs(mockInstance),
                "Entity shouldn't have the same identity when compared with a different entity");
    }

    @Test
    @SuppressWarnings({ "rawtypes", "unchecked" })
    void testSameIdentityAsWithSameEntity() {
        var entity = (Entity) instance;
        assertTrue(entity.sameIdentityAs(entity), "Entity should have the same identity when compared with itself");
    }

    @Test
    @SuppressWarnings("unchecked")
    void testSameIdentityAsWithEqualEntity() {
        var mockInstance = mock(instance.getClass());
        setField(mockInstance, "id", instance.id());
        assertTrue(
                instance.sameIdentityAs(mockInstance),
                "Entity should have the same identity when compared with an equal identity");
    }
}
