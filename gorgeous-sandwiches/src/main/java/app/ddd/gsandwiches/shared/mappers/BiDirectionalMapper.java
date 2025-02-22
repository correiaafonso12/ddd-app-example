package app.ddd.gsandwiches.shared.mappers;

/**
 * Mapper to translate from Source class to Target class and vice-versa
 */
public interface BiDirectionalMapper<S, T> extends Mapper<S, T> {

    /**
     * Maps target object to source object
     * 
     * @param target Target object to be mapped
     * @return Transformed source object
     */
    S reverseMap(T target);
}
