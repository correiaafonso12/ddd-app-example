package app.ddd.gsandwiches.shared.persistence;

/**
 * Mapper to translate from Domain to Schema and vice-versa
 */
public interface Mapper<D, S> {

    /**
     * Transforms given domain entity into a schema
     * 
     * @param domain Domain entity to be transformed
     * @return Result schema
     */
    public S toSchema(D domain);

    /**
     * Transforms given schema into a domain entity
     * 
     * @param schema Schema to be transformed
     * @return Result domain entity
     */
    public D toDomain(S schema);
}
