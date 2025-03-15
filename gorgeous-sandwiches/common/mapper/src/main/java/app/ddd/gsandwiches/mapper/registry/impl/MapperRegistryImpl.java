package app.ddd.gsandwiches.mapper.registry.impl;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Component;

import app.ddd.gsandwiches.mapper.Mapper;
import app.ddd.gsandwiches.mapper.registry.MapperRegistry;

@Component
class MapperRegistryImpl implements MapperRegistry {

    private final Map<Class<?>, Map<Class<?>, Mapper<?, ?>>> registry = new HashMap<>();

    @Override
    public void register(Mapper<?, ?> mapper) {
        registry.computeIfAbsent(mapper.sourceType(), k -> new HashMap<>()).put(mapper.targetType(), mapper);
    }

    @Override
    @SuppressWarnings("unchecked") // Safe cast because type is guaranteed by registry
    public <S, T> Optional<Mapper<S, T>> findFor(Class<S> source, Class<T> target) {
        return Optional.ofNullable(registry.get(source))
                .map(v -> v.get(target))
                .map(it -> (Mapper<S, T>) it);
    }

    @Override
    public <S, T> T findAndMap(S source, Class<T> target) {
        requireNonNull(source, "Source object must not be null");

        @SuppressWarnings("unchecked") // Safe cast because class is only used to check the registry for a Mapper
        var clazz = (Class<S>) source.getClass();

        return findFor(clazz, target)
                .map(mapper -> mapper.map(source))
                .orElseThrow(() -> new NoSuchElementException("%s -> %s Mapper not registered"
                        .formatted(clazz, target)));
    }

}
