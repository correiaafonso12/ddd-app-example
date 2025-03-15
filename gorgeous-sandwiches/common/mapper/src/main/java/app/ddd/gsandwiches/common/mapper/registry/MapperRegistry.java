package app.ddd.gsandwiches.common.mapper.registry;

import java.util.Optional;

import app.ddd.gsandwiches.common.mapper.Mapper;

/**
 * Generic registry for Mappers
 */
public interface MapperRegistry {

    /**
     * Register a new {@link Mapper} in the container
     * 
     * @param mapper Mapper to be registered
     */
    void register(Mapper<?, ?> mapper);

    /**
     * Checks if there is a {@link Mapper} in the container for the given types
     * 
     * @param <S>    Source type
     * @param <T>    Target type
     * @param source Class of source type
     * @param target Class of target type
     * @return Optional of fetched {@link Mapper}
     */
    <S, T> Optional<Mapper<S, T>> findFor(Class<S> source, Class<T> target);

    /**
     * Finds a {@link Mapper} for the type of given source object to target type. If
     * source is null throws {@link NullPointerException}. If a {@link Mapper} is
     * not found throws {@link NoSuchElementException}. If a {@link Mapper} is
     * found, uses it to map source value into target type
     * 
     * @param <S>    Source type
     * @param <T>    Target type
     * @param source Source object to be mapped
     * @param target Class of target type
     * @return Mapped object of target type
     */
    <S, T> T findAndMap(S source, Class<T> target);

}
