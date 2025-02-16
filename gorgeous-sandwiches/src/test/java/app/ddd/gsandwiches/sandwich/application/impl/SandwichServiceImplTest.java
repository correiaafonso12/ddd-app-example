package app.ddd.gsandwiches.sandwich.application.impl;

import static app.ddd.gsandwiches.sandwich.domain.SandwichTestFixture.EXPECTED_NAME;
import static app.ddd.gsandwiches.sandwich.domain.SandwichTestFixture.EXPECTED_SANDWICH;
import static app.ddd.gsandwiches.sandwich.domain.SandwichTestFixture.EXPECTED_SANDWICH_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.ddd.gsandwiches.sandwich.application.exceptions.SandwichWithNonUniqueIdException;
import app.ddd.gsandwiches.sandwich.application.exceptions.SandwichWithNonUniqueNameException;
import app.ddd.gsandwiches.sandwich.domain.Sandwich;
import app.ddd.gsandwiches.sandwich.domain.dao.SandwichDao;

public class SandwichServiceImplTest {

    private SandwichDao daoMock;
    private SandwichServiceImpl service;

    @BeforeEach
    public void setup() {
        daoMock = mock(SandwichDao.class);
        service = new SandwichServiceImpl(daoMock);
    }

    @Test
    void testGetAll() {
        var expected = List.of(EXPECTED_SANDWICH);
        when(daoMock.getAll()).thenReturn(expected);
        var result = service.getAll();
        assertEquals(expected, result, "Result list should be equal to provided list");
    }

    @Test
    void testGetByIdWithNullSandwichId() {
        when(daoMock.getById(null)).thenThrow(NullPointerException.class);
        assertThrows(
                NullPointerException.class,
                () -> service.getById(null),
                "Get by id with null id should throw");
    }

    @Test
    void testGetByIdWithSandwichIdOfNonExistingSandwich() {
        Optional<Sandwich> expected = Optional.empty();
        when(daoMock.getById(EXPECTED_SANDWICH_ID)).thenReturn(expected);
        var result = service.getById(EXPECTED_SANDWICH_ID);
        assertEquals(expected, result, "Get by id with id of non existing sandwich should return empty Optional");
    }

    @Test
    void testGetByIdWithSandwichIdOfExistingSandwich() {
        var expected = Optional.of(EXPECTED_SANDWICH);
        when(daoMock.getById(EXPECTED_SANDWICH_ID)).thenReturn(expected);
        var result = service.getById(EXPECTED_SANDWICH_ID);
        assertEquals(expected, result, "Get by id with id of existing sandwich should resturn Optional with sandwich");
    }

    @Test
    void testCreateWithNullSandwich() {
        assertThrows(
                NullPointerException.class,
                () -> service.create(null),
                "Create with null sandwich should throw");
    }

    @Test
    void testCreateWithExistingSandwichById() {
        when(daoMock.getById(EXPECTED_SANDWICH_ID)).thenReturn(Optional.of(EXPECTED_SANDWICH));
        var result = service.create(EXPECTED_SANDWICH);
        assertTrue(result.failed(), "Should not be able to create sandwich with duplicate id");
        assertInstanceOf(
                SandwichWithNonUniqueIdException.class,
                result.violation(),
                "Result violation should be of expected type");
    }

    @Test
    void testCreateWithExistingSandwichByName() {
        when(daoMock.getById(EXPECTED_SANDWICH_ID)).thenReturn(Optional.empty());
        when(daoMock.existsByName(EXPECTED_NAME)).thenReturn(true);
        var result = service.create(EXPECTED_SANDWICH);
        assertTrue(result.failed(), "Should not be able to create sandwich with duplicate name");
        assertInstanceOf(
                SandwichWithNonUniqueNameException.class,
                result.violation(),
                "Result violation should be of expected type");
    }

    @Test
    void testCreateNewSandwich() {
        when(daoMock.getById(EXPECTED_SANDWICH_ID)).thenReturn(Optional.empty());
        when(daoMock.existsByName(EXPECTED_NAME)).thenReturn(false);
        var result = service.create(EXPECTED_SANDWICH);
        assertTrue(result.succeeded(), "Should be able to create sandwich");
    }

}
