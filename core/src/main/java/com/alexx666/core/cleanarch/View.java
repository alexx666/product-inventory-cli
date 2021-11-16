package com.alexx666.core.cleanarch;

import java.util.Collection;

public interface View<T> {
    void present(T object);
    void present(Collection<T> objects);
}
