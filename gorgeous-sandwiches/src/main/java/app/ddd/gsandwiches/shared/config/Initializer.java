package app.ddd.gsandwiches.shared.config;

import jakarta.annotation.PostConstruct;

public abstract class Initializer {

    @PostConstruct
    protected abstract void initialize();
}
