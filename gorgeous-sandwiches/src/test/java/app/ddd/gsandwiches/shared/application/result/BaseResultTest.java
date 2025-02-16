package app.ddd.gsandwiches.shared.application.result;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public abstract class BaseResultTest<T extends BaseResult> {

    private T successfulResult;
    private T failedResult;

    protected abstract T createSuccessfulResult();

    protected abstract T createFailedResult();

    @BeforeEach
    public void setup() {
        successfulResult = createSuccessfulResult();
        failedResult = createFailedResult();
    }

    @Test
    void testSucceededWhenSuccessfulResult() {
        assertTrue(successfulResult.succeeded(), "Result should be successful");
    }

    @Test
    void testSucceededWhenFailedResult() {
        assertFalse(failedResult.succeeded(), "Result should be failed");
    }

    @Test
    void testFailedWhenSuccessfulResult() {
        assertFalse(successfulResult.failed(), "Result should be successful");
    }

    @Test
    void testFailedWhenFailedResult() {
        assertTrue(failedResult.failed(), "Result should be failed");
    }

}
