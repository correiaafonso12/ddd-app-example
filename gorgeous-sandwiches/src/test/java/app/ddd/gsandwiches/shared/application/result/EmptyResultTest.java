package app.ddd.gsandwiches.shared.application.result;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.function.Consumer;
import java.util.function.Supplier;

import org.junit.jupiter.api.Test;

public class EmptyResultTest extends BaseResultTest<EmptyResult> {

    @Override
    protected EmptyResult createSuccessfulResult() {
        return EmptyResult.success();
    }

    @Override
    protected EmptyResult createFailedResult() {
        return EmptyResult.failure(new Exception());
    }

    private Supplier<Boolean> supplyTrue() {
        return () -> true;
    }

    private Supplier<Boolean> supplyFalse() {
        return () -> false;
    }

    @Test
    void testCreateSuccessfulEmptyResult() {
        var result = createSuccessfulResult();
        assertNull(result.violation, "Violation should be null");
    }

    @Test
    void testCreateFailureEmptyResultWithNullException() {
        assertThrows(
                NullPointerException.class,
                () -> EmptyResult.failure(null),
                "Creation of a failed empty result with null exception should throw");
    }

    @Test
    void testCreateFailureEmptyResultWithValidException() {
        var result = createFailedResult();
        assertNotNull(result.violation, "Violation should not be null");
    }

    @Test
    void testVerifyWithNullSupplier() {
        var result = createSuccessfulResult();
        assertThrows(
                NullPointerException.class,
                () -> result.verify(null, null),
                "Null supplier should throw an exception");
    }

    @Test
    void testVerifyWithNullErrorSupplier() {
        var result = createSuccessfulResult();
        assertThrows(
                NullPointerException.class,
                () -> result.verify(supplyTrue(), null),
                "Null error supplier should throw an exception");
    }

    @Test
    void testVerifyWithFailedResult() {
        var result = createFailedResult();
        var verified = result.verify(supplyTrue(), Exception::new);
        assertEquals(result, verified, "Verified result should be initial result");
    }

    @Test
    void testVerifyWithSuccessfulResultAndTrueSupplier() {
        var result = createSuccessfulResult();
        var verified = result.verify(supplyTrue(), Exception::new);
        assertEquals(result, verified, "Verified result should be initial result");
    }

    @Test
    void testVerifyWithSuccessfulResultAndFalseSupplier() {
        var result = createSuccessfulResult();
        var verified = result.verify(supplyFalse(), Exception::new);
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
    void testOnSuccessWithSuccessfulResult() {
        var result = createSuccessfulResult();
        var runnableMock = mock(Runnable.class);
        result.onSuccess(runnableMock);
        verify(runnableMock).run();
    }

    @Test
    void testOnSuccessWithFailedResult() {
        var result = createFailedResult();
        var runnableMock = mock(Runnable.class);
        result.onSuccess(runnableMock);
        verify(runnableMock, never()).run();
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
    void testOrElseThrowWhenSuccessfulResult() {
        var result = createSuccessfulResult();
        assertDoesNotThrow(
                () -> result.orElseThrow(e -> e),
                "Nothing should happen when successful result");
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
    void testFoldWithNullValueSupplier() {
        var result = createSuccessfulResult();
        assertThrows(
                NullPointerException.class,
                () -> result.fold(null, e -> e),
                "Null value supplier should throw");
    }

    @Test
    void testFoldWithNullErrorFunction() {
        var result = createSuccessfulResult();
        assertThrows(
                NullPointerException.class,
                () -> result.fold(() -> "smt", null),
                "Null error function should throw");
    }

    @Test
    void testFoldWhenSuccessfulResult() {
        var result = createSuccessfulResult();
        var expected = "smt";
        var value = result.fold(() -> expected, e -> e);
        assertEquals(expected, value, "Should return expected value when succesful");
    }

    @Test
    void testFoldWhenFailedResult() {
        var result = createFailedResult();
        var value = result.fold(() -> "smt", e -> e);
        assertEquals(result.violation, value, "Should return expected exception when failed");
    }

}
