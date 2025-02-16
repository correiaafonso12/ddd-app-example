package app.ddd.gsandwiches.shared.application.result;

import java.util.function.Consumer;

/**
 * Base result type used by services to indicate success or failure.
 * <p>
 * Service operations should generally never throw checked exceptions. Instead,
 * they should return concrete result types
 * and raise unchecked exceptions only when an unexpected event happens, such as
 * a programming error.
 */
public abstract class BaseResult {

    protected final Exception violation;

    protected BaseResult(Exception violation) {
        this.violation = violation;
    }

    public Exception violation() {
        return violation;
    }

    public boolean succeeded() {
        return violation == null;
    }

    public boolean failed() {
        return !succeeded();
    }

    /**
     * Handle failed results
     * 
     * @param failureAction Consumer for failed action
     * @return Same {@code BaseResult} implementation
     */
    public abstract BaseResult onFailure(Consumer<Exception> failureAction);
}
