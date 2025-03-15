package app.ddd.gsandwiches.result;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.util.ReflectionTestUtils.getField;

import java.util.function.Consumer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ResultTest {

    // Example values and violations of a Result type
    private final String EXPECTED_VALUE = "smt";
    private final Exception EXPECTED_VIOLATION = new Exception();

    private Result<String> successfulResult;
    private Result<String> failedResult;

    @BeforeEach
    public void setup() {
        successfulResult = Result.success(EXPECTED_VALUE);
        failedResult = Result.failure(EXPECTED_VIOLATION);
    }

    private Object getResultValue(Result<?> result) {
        return getField(result, "value");
    }

    private Object getResultViolation(Result<?> result) {
        return getField(result, "violation");
    }

    @Test
    void testCreateSuccessfulVoidResult() {
        var result = Result.success();
        assertTrue(result.succeeded(), "Result should be in success state");
        assertNull(getResultValue(result), "Result value should be null");
        assertNull(getResultViolation(result), "Result violation should be null");
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
        assertTrue(successfulResult.succeeded(), "Result should be in success state");
        assertEquals(EXPECTED_VALUE, getResultValue(successfulResult), "Result value should be expected value");
        assertNull(getResultViolation(successfulResult), "Result violation should be null");
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
        assertTrue(failedResult.failed(), "Result should be in failed state");
        assertNull(getResultValue(failedResult), "Value should be null");
        assertEquals(EXPECTED_VIOLATION, getResultViolation(failedResult), "Violation should be expected violation");
    }

    @Test
    void testFromWithNullCallable() {
        assertThrows(
                NullPointerException.class,
                () -> Result.from(null),
                "Creation of a Result from null callable should throw");
    }

    @Test
    void testFromWithSuccessfulCallable() {
        var result = Result.from(() -> EXPECTED_VALUE);
        assertTrue(result.succeeded(), "Result should be successful");
        assertEquals(EXPECTED_VALUE, getResultValue(result), "Result value should be expected value");
        assertNull(getResultViolation(result), "Result violation should be null");
    }

    @Test
    void testFromWithFailedCallable() {
        var result = Result.from(() -> {
            throw EXPECTED_VIOLATION;
        });
        assertTrue(result.failed(), "Result should be failure");
        assertNull(getResultValue(result), "Value should be null");
        assertEquals(EXPECTED_VIOLATION, getResultViolation(result), "Violation should be expected violation");
    }

    @Test
    void testVerifyWithNullPredicate() {
        assertThrows(
                NullPointerException.class,
                () -> successfulResult.verify(null, null),
                "Null predicate should throw an exception");
    }

    @Test
    void testVerifyWithNullErrorSupplier() {
        assertThrows(
                NullPointerException.class,
                () -> successfulResult.verify(value -> true, null),
                "Null error supplier should throw an exception");
    }

    @Test
    void testVerifyWithFailedResult() {
        var verified = failedResult.verify(value -> true, Exception::new);
        assertEquals(failedResult, verified, "Verified result should be initial result");
    }

    @Test
    void testVerifyWithSuccessfulResultAndTruePredicate() {
        var verified = successfulResult.verify(value -> true, Exception::new);
        assertEquals(successfulResult, verified, "Verified result should be initial result");
    }

    @Test
    void testVerifyWithSuccessfulResultAndFalsePredicate() {
        var verified = successfulResult.verify(value -> false, Exception::new);
        assertTrue(verified.failed(), "Verified result should be failed");
    }

    @Test
    void testMapWithNullFunction() {
        assertThrows(
                NullPointerException.class,
                () -> successfulResult.map(null),
                "Null function should throw an exception");
    }

    @Test
    void testMapWithSuccessfulResult() {
        var intValue = 123;
        var mapped = successfulResult.map(value -> intValue);
        assertTrue(mapped.succeeded(), "Mapped result should be successful");
        assertEquals(intValue, getResultValue(mapped), "Mapped result value should be mapped value");
    }

    @Test
    void testMapWithFailedResult() {
        var mapped = failedResult.map(Integer::parseInt);
        assertTrue(mapped.failed(), "Mapped result should be failure");
        assertEquals(
                EXPECTED_VIOLATION,
                getResultViolation(mapped),
                "Mapped result violation should be current violation");
    }

    @Test
    void testFlatMapWithNullFunction() {
        assertThrows(
                NullPointerException.class,
                () -> successfulResult.flatMap(null),
                "Null function should throw an exception");
    }

    @Test
    void testFlatMapWithSuccessfulResultFromSuccessfulResult() {
        var mapped = successfulResult.flatMap(Result::success);
        assertTrue(mapped.succeeded(), "Mapped result should be successful");
    }

    @Test
    void testFlatMapWithSuccessfulResultFromFailedResult() {
        var mapped = successfulResult.flatMap(value -> Result.failure(EXPECTED_VIOLATION));
        assertTrue(mapped.failed(), "Mapped result should be failure");
    }

    @Test
    void testFlatMapWithFailedResult() {
        var mapped = failedResult.flatMap(Result::success);
        assertTrue(mapped.failed(), "Mapped result should be failure");
        assertEquals(
                EXPECTED_VIOLATION,
                getResultViolation(mapped),
                "Mapped result violation should be current violation");
    }

    @Test
    void testOnSuccessWithNullAction() {
        assertThrows(
                NullPointerException.class,
                () -> successfulResult.onSuccess(null),
                "Null action should throw an exception");
    }

    @Test
    @SuppressWarnings("unchecked")
    void testOnSuccessWithSuccessfulResult() {
        var consumerMock = mock(Consumer.class);
        successfulResult.onSuccess(consumerMock);
        verify(consumerMock).accept(any());
    }

    @Test
    @SuppressWarnings("unchecked")
    void testOnSuccessWithFailedResult() {
        var consumerMock = mock(Consumer.class);
        failedResult.onSuccess(consumerMock);
        verify(consumerMock, never()).accept(any());
    }

    @Test
    void testOnFailureWithNullAction() {
        assertThrows(
                NullPointerException.class,
                () -> failedResult.onFailure(null),
                "Null action should throw an exception");
    }

    @Test
    @SuppressWarnings("unchecked")
    void testOnFailureWithSuccessfulResult() {
        var exceptionConsumerMock = mock(Consumer.class);
        successfulResult.onFailure(exceptionConsumerMock);
        verify(exceptionConsumerMock, never()).accept(any());
    }

    @Test
    @SuppressWarnings("unchecked")
    void testOnFailureWithFailedResult() {
        var exceptionConsumerMock = mock(Consumer.class);
        failedResult.onFailure(exceptionConsumerMock);
        verify(exceptionConsumerMock).accept(any());
    }

    @Test
    void testFoldWithNullValueFunction() {
        assertThrows(
                NullPointerException.class,
                () -> successfulResult.fold(null, e -> e),
                "Null value function should throw");
    }

    @Test
    void testFoldWithNullErrorFunction() {
        assertThrows(
                NullPointerException.class,
                () -> successfulResult.fold(val -> val, null),
                "Null error function should throw");
    }

    @Test
    void testFoldWhenSuccessfulResult() {
        var value = successfulResult.fold(val -> val, e -> e);
        assertEquals(EXPECTED_VALUE, value, "Should return expected value when succesful");
    }

    @Test
    void testFoldWhenFailedResult() {
        var value = failedResult.fold(val -> val, e -> e);
        assertEquals(EXPECTED_VIOLATION, value, "Should return expected exception when failed");
    }

    @Test
    void testOrElseWithSuccessfulResult() {
        var value = successfulResult.orElse("other");
        assertEquals(EXPECTED_VALUE, value, "Value should be expected value");
    }

    @Test
    void testOrElseWithFailedResult() {
        var other = "other";
        var value = failedResult.orElse(other);
        assertEquals(other, value, "Value should be other value");
    }

    @Test
    void testOrElseGetWithSuccessfulResult() {
        var value = successfulResult.orElseGet(e -> "other");
        assertEquals(EXPECTED_VALUE, value, "Value should be expected value");
    }

    @Test
    void testOrElseGetWithFailedResult() {
        var other = "other";
        var value = failedResult.orElseGet(e -> other);
        assertEquals(other, value, "Value should be other value");
    }

    @Test
    void testOrElseGetWithNullFunction() {
        assertThrows(
                NullPointerException.class,
                () -> successfulResult.orElseGet(null),
                "Null error function should throw");
    }

    @Test
    void testOrElseThrowWithNullExceptionMapper() {
        assertThrows(
                NullPointerException.class,
                () -> successfulResult.orElseThrow(null),
                "Null exception mapper should throw");
    }

    @Test
    void testOrElseThrowWhenSuccessfulResult() throws Exception {
        var value = successfulResult.orElseThrow(e -> e);
        assertEquals(EXPECTED_VALUE, value, "Should return current value when successful");
    }

    @Test
    void testOrElseThrowWhenFailedResult() {
        assertThrows(
                EXPECTED_VIOLATION.getClass(),
                () -> failedResult.orElseThrow(e -> e),
                "Should throw exception resulting from exception mapper");
    }
}
