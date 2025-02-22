package app.ddd.gsandwiches.shared.mappers;

/**
 * Mapper to translate from Source class to Target class
 */
public interface Mapper<S, T> {

    /**
     * Maps source object to target object
     * 
     * @param source Source object to be mapped
     * @return Transformed target object
     */
    public T map(S source);
}
