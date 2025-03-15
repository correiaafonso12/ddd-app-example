package app.ddd.gsandwiches.mapper.registry.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.ddd.gsandwiches.mapper.Mapper;
import app.ddd.gsandwiches.mapper.registry.MapperRegistry;

public class MapperRegistryImplTest {

    private MapperRegistry registry;

    @BeforeEach
    public void setup() {
        registry = new MapperRegistryImpl();
    }

    @SuppressWarnings("unchecked")
    private <S, T> Mapper<S, T> createAndRegisterMockMapper(Class<S> sourceType, Class<T> targetType) {
        var mapperMock = (Mapper<S, T>) mock(Mapper.class);
        when(mapperMock.sourceType()).thenReturn(sourceType);
        when(mapperMock.targetType()).thenReturn(targetType);
        registry.register(mapperMock);
        return mapperMock;
    }

    @Test
    void testFindForUnregisteredMapper() {
        var optionalMapper = registry.findFor(String.class, Integer.class);
        assertFalse(optionalMapper.isPresent(), "Mapper should not be present");
    }

    @Test
    void testFindForRegisteredMapper() {
        var sourceType = String.class;
        var targetType = Integer.class;
        var mapperMock = createAndRegisterMockMapper(sourceType, targetType);
        var optionalMapper = registry.findFor(sourceType, targetType);
        assertTrue(optionalMapper.isPresent(), "Mapper should be present");
        assertEquals(mapperMock, optionalMapper.get(), "Mapper should be registered mapper");
    }

    @Test
    void testFindForWithNullSourceType() {
        var optionalMapper = registry.findFor(null, String.class);
        assertFalse(optionalMapper.isPresent(), "Mapper should not be present");
    }

    @Test
    void testFindForWithNullTargetType() {
        var optionalMapper = registry.findFor(String.class, null);
        assertFalse(optionalMapper.isPresent(), "Mapper should not be present");
    }

    @Test
    void testFindAndMapWithNullSource() {
        assertThrows(
                NullPointerException.class,
                () -> registry.findAndMap(null, String.class),
                "Find and map with null source should throw");
    }

    @Test
    void testFindAndMapWithUnregisteredMapper() {
        assertThrows(
                NoSuchElementException.class,
                () -> registry.findAndMap(123, String.class),
                "Find and map with unregistered Mapper should throw");
    }

    @Test
    void testFindAndMapWithRegisteredMapper() {
        var source = 123;
        var targetType = String.class;
        var expected = "123";
        var mapperMock = createAndRegisterMockMapper(Integer.class, targetType);
        when(mapperMock.map(source)).thenReturn(expected);
        var value = registry.findAndMap(source, targetType);
        assertEquals(expected, value, "Should find Mapper and map to expected value");
    }

    @Test
    void testFindAndMapWithSubclassSourceType() {
        var source = new RuntimeException(); // Example subclass of Exception
        var targetType = String.class;
        createAndRegisterMockMapper(Exception.class, targetType);
        assertThrows(
                NoSuchElementException.class,
                () -> registry.findAndMap(source, targetType),
                "Should not find Mapper for subclass of source type");
    }

}
