package com.alexx666.core.cleanarch;

import java.util.Collection;

public interface View<T> {
    /**
     * Present a single {@link T} object to the user
     *
     * @param object {@link T} to be presented
     */
    void present(T object);

    /**
     * Presents a collection of {@link T} to the user
     *
     * @param objects a {@link Collection<T>} objects
     */
    void present(Collection<T> objects);
}
