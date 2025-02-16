package app.ddd.gsandwiches.shared.application.result;

import static java.util.Objects.requireNonNull;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Represents a result without data. Use to return success or failure from an
 * operation. In case of failure, handle the underlying {@code Exception}
 * <p>
 * Instances of {@code EmptyResult} should be created with
 * {@code Result.empty()}
 */
public class EmptyResult extends BaseResult {

    private EmptyResult(Exception violation) {
        super(violation);
    }

    static EmptyResult success() {
        return new EmptyResult(null);
    }

    static EmptyResult failure(Exception exception) {
        requireNonNull(exception);
        return new EmptyResult(exception);
    }

    /**
     * Verifies if a condition is true. If false, supplies error. If already failed,
     * skips execution
     * 
     * @param supplier      Boolean condition to analyse
     * @param errorSupplier Exception supplier in case of falseness
     * @return The same {@code EmptyResult} if already failed, or condition is true.
     *         A failed {@code EmptyResult} otherwise
     */
    public EmptyResult verify(Supplier<Boolean> supplier, Supplier<? extends Exception> errorSupplier) {
        requireNonNull(supplier);
        requireNonNull(errorSupplier);

        if (failed() || supplier.get()) {
            return this;
        }
        return failure(errorSupplier.get());
    }

    /**
     * Handle successful results
     * 
     * @param successAction Runnable action
     * @return Same {@code EmptyResult}
     */
    public EmptyResult onSuccess(Runnable successAction) {
        requireNonNull(successAction);
        if (succeeded()) {
            successAction.run();
        }
        return this;
    }

    @Override
    public EmptyResult onFailure(Consumer<Exception> failureAction) {
        requireNonNull(failureAction);
        if (failed()) {
            failureAction.accept(violation);
        }
        return this;
    }

    /**
     * Throws an exception returned by the mapper {@link Function} if not
     * successful.
     * 
     * @param <E>             Result throwable type
     * @param exceptionMapper The function that maps current violation into another
     *                        exception.
     */
    public <E extends Throwable> void orElseThrow(Function<Exception, E> exceptionMapper) throws E {
        requireNonNull(exceptionMapper);
        if (failed()) {
            throw exceptionMapper.apply(violation);
        }
    }

    /**
     * Retrieve a value from this {@code EmptyResult} by folding the states. If in
     * success state, return the value provided by the value-supplier. If in error
     * state, return the value of applying the error-function to the error value.
     * 
     * @param <T>           Desired return type
     * @param valueSupplier The supplier that produces the desired result when
     *                      successful
     * @param errorFunction The function that produces the desired result when
     *                      failed
     * @return Result produces by either valueSupplier or errorFunction
     */
    public <T> T fold(
            Supplier<? extends T> valueSupplier,
            Function<Exception, ? extends T> errorFunction) {
        requireNonNull(valueSupplier);
        requireNonNull(errorFunction);
        return succeeded() ? valueSupplier.get() : errorFunction.apply(violation);
    }

}
