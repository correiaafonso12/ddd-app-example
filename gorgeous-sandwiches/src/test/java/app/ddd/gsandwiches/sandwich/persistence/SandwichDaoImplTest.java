package app.ddd.gsandwiches.sandwich.persistence;

import static app.ddd.gsandwiches.sandwich.domain.SandwichTestFixture.EXPECTED_NAME;
import static app.ddd.gsandwiches.sandwich.domain.SandwichTestFixture.EXPECTED_SANDWICH;
import static app.ddd.gsandwiches.sandwich.domain.SandwichTestFixture.EXPECTED_SANDWICH_ID;
import static app.ddd.gsandwiches.sandwich.persistence.SandwichSchemaTestFixture.EXPECTED_SANDWICH_SCHEMA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.ddd.gsandwiches.sandwich.domain.Sandwich;
import app.ddd.gsandwiches.sandwich.persistence.repositories.SandwichRepository;
import app.ddd.gsandwiches.sandwich.persistence.schema.SandwichSchema;
import app.ddd.gsandwiches.shared.persistence.Mapper;

public class SandwichDaoImplTest {

    private SandwichRepository repositoryMock;
    private Mapper<Sandwich, SandwichSchema> mapperMock;
    private SandwichDaoImpl dao;

    @BeforeEach
    @SuppressWarnings("unchecked")
    public void setup() {
        repositoryMock = mock(SandwichRepository.class);
        mapperMock = mock(Mapper.class);
        dao = new SandwichDaoImpl(repositoryMock, mapperMock);
    }

    @Test
    void testGetAllFromEmptyList() {
        when(repositoryMock.findAll()).thenReturn(List.of());
        var sandwichList = dao.getAll();
        assertTrue(sandwichList.isEmpty(), "Should return empty list");
    }

    @Test
    void testGetAll() {
        var expected = List.of(EXPECTED_SANDWICH_SCHEMA);
        when(repositoryMock.findAll()).thenReturn(expected);
        var sandwichList = dao.getAll();
        assertEquals(expected.size(), sandwichList.size(), "Expected list size and actual list size should match");
    }

    @Test
    void testGetByIdWithNullId() {
        assertThrows(
                NullPointerException.class,
                () -> dao.getById(null),
                "Get by id with null id should throw");
    }

    @Test
    void testGetByIdWithNonExistingId() {
        when(repositoryMock.findBySandwichId(any())).thenReturn(Optional.empty());
        var sandwichOptional = dao.getById(EXPECTED_SANDWICH_ID);
        assertTrue(sandwichOptional.isEmpty(), "Optional result should not have a value");
    }

    @Test
    void testGetByIdWithExistingId() {
        when(repositoryMock.findBySandwichId(any())).thenReturn(Optional.of(EXPECTED_SANDWICH_SCHEMA));
        when(mapperMock.toDomain(EXPECTED_SANDWICH_SCHEMA)).thenReturn(EXPECTED_SANDWICH);
        var sandwichOptional = dao.getById(EXPECTED_SANDWICH_ID);
        assertTrue(sandwichOptional.isPresent(), "Optional result should have a value");
    }

    @Test
    void testExistsByNameWithNullName() {
        assertThrows(
                NullPointerException.class,
                () -> dao.existsByName(null),
                "Exists by name with null name should throw");
    }

    @Test
    void testExistsByNameWithNonExistingName() {
        when(repositoryMock.findByName(any())).thenReturn(Optional.empty());
        assertFalse(dao.existsByName(EXPECTED_NAME), "Should not exist by name");
    }

    @Test
    void testExistsByNameWithExistingName() {
        when(repositoryMock.findByName(any())).thenReturn(Optional.of(EXPECTED_SANDWICH_SCHEMA));
        assertTrue(dao.existsByName(EXPECTED_NAME), "Should exist by name");
    }

}
