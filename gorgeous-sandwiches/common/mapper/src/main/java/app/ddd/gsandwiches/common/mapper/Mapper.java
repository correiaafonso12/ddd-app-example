package app.ddd.gsandwiches.common.mapper;

/**
 * Mapper to translate from Source class to Target class
 */
public interface Mapper<S, T> {

    /**
     * Get source type
     * 
     * @return Source type class
     */
    Class<S> sourceType();

    /**
     * Get target type
     * 
     * @return Target type class
     */
    Class<T> targetType();

    /**
     * Maps source object to target object
     * 
     * @param source Source object to be mapped
     * @return Transformed target object
     */
    T map(S source);
}
