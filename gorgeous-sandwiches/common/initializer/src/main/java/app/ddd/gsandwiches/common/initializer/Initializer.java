package app.ddd.gsandwiches.common.initializer;

import jakarta.annotation.PostConstruct;

/**
 * Generic class to initialize objects during application start.
 */
public abstract class Initializer {

    /**
     * All dependencies will have been injected during the execution of this method.
     * Use it to register components.
     */
    @PostConstruct
    protected abstract void initialize();
}
