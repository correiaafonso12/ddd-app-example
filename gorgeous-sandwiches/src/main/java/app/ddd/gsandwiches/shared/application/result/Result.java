package app.ddd.gsandwiches.shared.application.result;

import static java.util.Objects.requireNonNull;

import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Represents the result value from an operation. Use to return success with a
 * value of type {@code T}, or failure with the underlying {@code Exception}
 */
public class Result<T> extends BaseResult {

    private final T value;

    private Result(T value, Exception violation) {
        super(violation);
        this.value = value;
    }

    public static <T> Result<T> success(T value) {
        requireNonNull(value);
        return new Result<T>(value, null);
    }

    public static <T> Result<T> failure(Exception exception) {
        requireNonNull(exception);
        return new Result<T>(null, exception);
    }

    /**
     * Creates an {@code EmptyResult}
     * 
     * @return new successful {@code EmptyResult}
     */
    public static EmptyResult empty() {
        return EmptyResult.success();
    }

    /**
     * Creates a {@code Result<T>} by handling a {@code Callable<T>}.
     * 
     * @param <T>      Result type
     * @param callable Callable task
     * @return Successful {@code Result<T>} with the return value if no exception is
     *         thrown. Failed {@code Result<T>} with the thrown exception otherwise.
     */
    public static <T> Result<T> from(Callable<T> callable) {
        requireNonNull(callable);
        try {
            return success(callable.call());
        } catch (Exception e) {
            return failure(e);
        }
    }

    /**
     * Verifies if a condition is true. If false, supplies error. If already failed,
     * skips execution
     * 
     * @param predicate     Boolean condition to analyse
     * @param errorSupplier Exception supplier in case of falseness
     * @return The same {@code Result<T>} if already failed, or condition is true.
     *         A failed {@code Result<T>} otherwise
     */
    public Result<T> verify(Predicate<T> predicate, Supplier<? extends Exception> errorSupplier) {
        requireNonNull(predicate);
        requireNonNull(errorSupplier);

        if (failed() || predicate.test(value)) {
            return this;
        }
        return failure(errorSupplier.get());
    }

    /**
     * Handle successful results
     * 
     * @param successAction Consumer for successful action
     * @return Same {@code Result<T>}
     */
    public Result<T> onSuccess(Consumer<T> successAction) {
        requireNonNull(successAction);
        if (succeeded()) {
            successAction.accept(value);
        }
        return this;
    }

    @Override
    public Result<T> onFailure(Consumer<Exception> failureAction) {
        requireNonNull(failureAction);
        if (failed()) {
            failureAction.accept(violation);
        }
        return this;
    }

    /**
     * Throws an exception returned by the mapper {@link Function} if not
     * successful. Otherwise, return current value.
     * 
     * @param <E>             Result throwable type
     * @param exceptionMapper The function that maps current violation into another
     *                        exception.
     * @return The current value if successful.
     */
    public <E extends Throwable> T orElseThrow(Function<Exception, E> exceptionMapper) throws E {
        requireNonNull(exceptionMapper);
        if (failed()) {
            throw exceptionMapper.apply(violation);
        }
        return value;
    }

    /**
     * Retrieve a value from this {@code Result} by folding the states. If in
     * success state, return the value provided by the value-function. If in error
     * state, return the value of applying the error-function to the error value.
     * 
     * @param <N>           Expected result type
     * @param valueFunction The function that produces the desired result when
     *                      successful
     * @param errorFunction The function that produces the desired result when
     *                      failed
     * @return Result produces by either valueFunction or errorFunction
     */
    public <N> N fold(
            Function<T, ? extends N> valueFunction,
            Function<Exception, ? extends N> errorFunction) {
        requireNonNull(valueFunction);
        requireNonNull(errorFunction);
        return succeeded() ? valueFunction.apply(value) : errorFunction.apply(violation);
    }

}
