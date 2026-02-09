package app.ddd.gsandwiches.common.result;

import static java.util.Objects.requireNonNull;

import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Result type used to indicate success or failure. Represents the result value
 * from an operation, containing a value of type {@code T} if in success state,
 * or an exception in error state.
 * 
 * <p>
 * Service operations should generally never throw checked exceptions. Instead,
 * they should return concrete result types and raise unchecked exceptions only
 * when an unexpected event happens, such as a programming error.
 * </p>
 * 
 * @param <T> the type of the success value
 */
public class Result<T> {

    private final T value;
    private final Exception violation;

    private Result(T value, Exception violation) {
        this.value = value;
        this.violation = violation;
    }

    /**
     * Create a {@code Result} with {@code Void} sucess value in success state
     * 
     * @return a new successful {@code Result} with {@code Void} sucess value
     */
    public static Result<Void> success() {
        return new Result<Void>(null, null);
    }

    /**
     * Create a {@code Result} in success state
     * 
     * @param value success value
     * @return a new successful {@code Result}
     * @throws NullPointerException if value is null
     */
    public static <T> Result<T> success(T value) {
        requireNonNull(value);
        return new Result<T>(value, null);
    }

    /**
     * Create a {@code Result} in failed state
     * 
     * @param exception exception representing result violation
     * @return a new failed {@code Result}
     * @throws NullPointerException if exception is null
     */
    public static <T> Result<T> failure(Exception exception) {
        requireNonNull(exception);
        return new Result<T>(null, exception);
    }

    /**
     * Creates a {@code Result} by handling a {@code Callable<T>}.
     * 
     * @param callable callable task
     * @return a successful {@code Result} with the callable's return value if no
     *         exception is thrown. Failed {@code Result} with the thrown
     *         exception otherwise.
     * @throws NullPointerException if callable is null
     */
    public static <T> Result<T> from(Callable<T> callable) {
        requireNonNull(callable);
        try {
            return success(callable.call());
        } catch (Exception e) {
            return failure(e);
        }
    }

    public boolean succeeded() {
        return violation == null;
    }

    public boolean failed() {
        return !succeeded();
    }

    /**
     * Verifies if a condition is true. If false, supplies error. If already failed,
     * skips execution
     * 
     * @param predicate     boolean condition to analyze
     * @param errorSupplier exception supplier in case the condition evaluates to
     *                      {@code false}
     * @return the same {@code Result} if already failed, or condition is true.
     *         A failed {@code Result} otherwise
     * @throws NullPointerException if predicate or errorSupplier are null
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
     * Map the {@code Result} success value into another type by a mapping function
     * 
     * @param <N>      success target type returned by the mapping function
     * @param function mapping function
     * @return a new successful {@code Result} with the mapped value if in success
     *         state, or a new failed {@code Result} with the current violation
     * @throws NullPointerException if function is null
     */
    public <N> Result<N> map(Function<T, N> function) {
        requireNonNull(function);
        return succeeded() ? success(function.apply(value)) : failure(violation);
    }

    /**
     * Map the {@code Result} success value into the success value of a
     * {@code Result} returned by a mapping function
     * 
     * @param <N>      success target type from the {@code Result} returned by the
     *                 mapping function
     * @param function mapping function
     * @return the {@code Result} returned by the mapping function if in success
     *         state, or a new failed {@code Result} with the current violation if
     *         in failed state
     * @throws NullPointerException if function is null
     */
    public <N> Result<N> flatMap(Function<T, Result<N>> function) {
        requireNonNull(function);
        return succeeded() ? function.apply(value) : failure(violation);
    }

    /**
     * Handle successful results
     * 
     * @param successAction consumer for success action
     * @return same {@code Result}
     * @throws NullPointerException if success action is null
     */
    public Result<T> onSuccess(Consumer<T> successAction) {
        requireNonNull(successAction);
        if (succeeded()) {
            successAction.accept(value);
        }
        return this;
    }

    /**
     * Handle failed results
     * 
     * @param failureAction consumer for failure action
     * @return same {@code Result}
     * @throws NullPointerException if failure action is null
     */
    public Result<T> onFailure(Consumer<Exception> failureAction) {
        requireNonNull(failureAction);
        if (failed()) {
            failureAction.accept(violation);
        }
        return this;
    }

    /**
     * Retrieve a value from this {@code Result} by folding the states. If in
     * success state, return the value provided by the value function. If in error
     * state, return the value provided by the error function.
     * 
     * @param <N>           target type from applying the value function
     * @param valueFunction mapping function for successful state
     * @param errorFunction mapping function for failed state
     * @return value produced by either the value function or error function
     * @throws NullPointerException if value function or error function are null
     */
    public <N> N fold(
            Function<T, N> valueFunction,
            Function<Exception, N> errorFunction) {
        requireNonNull(valueFunction);
        requireNonNull(errorFunction);
        return succeeded() ? valueFunction.apply(value) : errorFunction.apply(violation);
    }

    /**
     * Get success value if in success state, or other value if in failed state
     * 
     * @param other other value
     * @return success value or other value
     */
    public T orElse(T other) {
        return succeeded() ? value : other;
    }

    /**
     * Get success value if in success state, or value returned by function if in
     * failed state
     * 
     * @param function other function
     * @return success value or value returned by function
     * @throws NullPointerException if function is null
     */
    public T orElseGet(Function<Exception, T> function) {
        requireNonNull(function);
        return succeeded() ? value : function.apply(violation);
    }

    /**
     * Get success value if in success state, or throw an exception returned by the
     * exception mapper function if in failed state
     * 
     * @param <E>             type of target exception
     * @param exceptionMapper function returning a throwable to be thrown if in
     *                        failed state
     * @return success value
     * @throws NullPointerException if exception mapper is null
     * @throws E                    if in failed state
     */
    public <E extends Throwable> T orElseThrow(Function<Exception, E> exceptionMapper) throws E {
        requireNonNull(exceptionMapper);
        if (failed()) {
            throw exceptionMapper.apply(violation);
        }
        return value;
    }

}
