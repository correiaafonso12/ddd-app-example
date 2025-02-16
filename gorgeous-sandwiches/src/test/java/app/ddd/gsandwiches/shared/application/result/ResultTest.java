package app.ddd.gsandwiches.shared.application.result;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.util.ReflectionTestUtils.getField;

import java.util.function.Consumer;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

public class ResultTest extends BaseResultTest<Result<?>> {

    // String used as an example of a Result type

    private final String EXPECTED_VALUE = "smt";

    @Override
    protected Result<String> createSuccessfulResult() {
        return Result.success(EXPECTED_VALUE);
    }

    @Override
    protected Result<String> createFailedResult() {
        return Result.failure(new Exception());
    }

    private Predicate<String> testTrue() {
        return value -> true;
    }

    private Predicate<String> testFalse() {
        return value -> false;
    }

    @Test
    void testCreateSuccessfulResultWithNullValue() {
        assertThrows(
                NullPointerException.class,
                () -> Result.success(null),
                "Creation of a successful result with null value should throw");
    }

    @Test
    void testCreateSuccessfulResultWithValidValue() {
        var result = createSuccessfulResult();
        var value = getField(result, "value");
        assertNotNull(value, "Value should have been defined");
        assertNull(result.violation, "Violation should be null");
    }

    @Test
    void testCreateFailureResultWithNullException() {
        assertThrows(
                NullPointerException.class,
                () -> Result.failure(null),
                "Creation of a failed result with null exception should throw");
    }

    @Test
    void testCreateFailureResultWithValidException() {
        var result = createFailedResult();
        var value = getField(result, "value");
        assertNull(value, "Value should be null");
        assertNotNull(result.violation, "Violation should not be null");
    }

    @Test
    void testCreateEmptyResult() {
        var result = Result.empty();
        assertInstanceOf(EmptyResult.class, result, "Result should be an instance of EmptyResult");
        assertTrue(result.succeeded(), "Result should be successful");
    }

    @Test
    void testFromWithNullCallable() {
        assertThrows(
                NullPointerException.class,
                () -> Result.from(null),
                "Creation of a from null callable should throw");
    }

    @Test
    void testFromWithSuccessfulCallable() {
        var result = Result.from(() -> "smt");
        assertTrue(result.succeeded(), "Result should be successful");
    }

    @Test
    void testFromWithFailedCallable() {
        var result = Result.from(() -> {
            throw new Exception();
        });
        assertTrue(result.failed(), "Result should be failure");
    }

    @Test
    void testVerifyWithNullPredicate() {
        var result = createSuccessfulResult();
        assertThrows(
                NullPointerException.class,
                () -> result.verify(null, null),
                "Null predicate should throw an exception");
    }

    @Test
    void testVerifyWithNullErrorSupplier() {
        var result = createSuccessfulResult();
        assertThrows(
                NullPointerException.class,
                () -> result.verify(testTrue(), null),
                "Null error supplier should throw an exception");
    }

    @Test
    void testVerifyWithFailedResult() {
        var result = createFailedResult();
        var verified = result.verify(testTrue(), Exception::new);
        assertEquals(result, verified, "Verified result should be initial result");
    }

    @Test
    void testVerifyWithSuccessfulResultAndTruePredicate() {
        var result = createSuccessfulResult();
        var verified = result.verify(testTrue(), Exception::new);
        assertEquals(result, verified, "Verified result should be initial result");
    }

    @Test
    void testVerifyWithSuccessfulResultAndFalsePredicate() {
        var result = createSuccessfulResult();
        var verified = result.verify(testFalse(), Exception::new);
        assertTrue(verified.failed(), "Verified result should be failed");
    }

    @Test
    void testOnSuccessWithNullAction() {
        var result = createSuccessfulResult();
        assertThrows(
                NullPointerException.class,
                () -> result.onSuccess(null),
                "Null action should throw an exception");
    }

    @Test
    @SuppressWarnings("unchecked")
    void testOnSuccessWithSuccessfulResult() {
        var result = createSuccessfulResult();
        var consumerMock = mock(Consumer.class);
        result.onSuccess(consumerMock);
        verify(consumerMock).accept(any());
    }

    @Test
    @SuppressWarnings("unchecked")
    void testOnSuccessWithFailedResult() {
        var result = createFailedResult();
        var consumerMock = mock(Consumer.class);
        result.onSuccess(consumerMock);
        verify(consumerMock, never()).accept(any());
    }

    @Test
    void testOnFailureWithNullAction() {
        var result = createSuccessfulResult();
        assertThrows(
                NullPointerException.class,
                () -> result.onFailure(null),
                "Null action should throw an exception");
    }

    @Test
    @SuppressWarnings("unchecked")
    void testOnFailureWithSuccessfulResult() {
        var result = createSuccessfulResult();
        var exceptionConsumerMock = mock(Consumer.class);
        result.onFailure(exceptionConsumerMock);
        verify(exceptionConsumerMock, never()).accept(any());
    }

    @Test
    @SuppressWarnings("unchecked")
    void testOnFailureWithFailedResult() {
        var result = createFailedResult();
        var exceptionConsumerMock = mock(Consumer.class);
        result.onFailure(exceptionConsumerMock);
        verify(exceptionConsumerMock).accept(any());
    }

    @Test
    void testOrElseThrowWithNullExceptionMapper() {
        var result = createSuccessfulResult();
        assertThrows(
                NullPointerException.class,
                () -> result.orElseThrow(null),
                "Null exception mapper should throw");
    }

    @Test
    void testOrElseThrowWhenSuccessfulResult() throws Exception {
        var result = createSuccessfulResult();
        var value = result.orElseThrow(e -> e);
        assertEquals(EXPECTED_VALUE, value, "Should return current value when successful");
    }

    @Test
    void testOrElseThrowWhenFailedResult() {
        var result = createFailedResult();
        assertThrows(
                Exception.class,
                () -> result.orElseThrow(e -> e),
                "Should throw exception resulting from exception mapper");
    }

    @Test
    void testFoldWithNullValueFunction() {
        var result = createSuccessfulResult();
        assertThrows(
                NullPointerException.class,
                () -> result.fold(null, e -> e),
                "Null value function should throw");
    }

    @Test
    void testFoldWithNullErrorFunction() {
        var result = createSuccessfulResult();
        assertThrows(
                NullPointerException.class,
                () -> result.fold(val -> val, null),
                "Null error function should throw");
    }

    @Test
    void testFoldWhenSuccessfulResult() {
        var result = createSuccessfulResult();
        var value = result.fold(val -> val, e -> e);
        assertEquals(EXPECTED_VALUE, value, "Should return expected value when succesful");
    }

    @Test
    void testFoldWhenFailedResult() {
        var result = createFailedResult();
        var value = result.fold(val -> val, e -> e);
        assertEquals(result.violation, value, "Should return expected exception when failed");
    }

}
