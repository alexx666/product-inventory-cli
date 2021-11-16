package com.alexx666.cqrs.util;

import java.util.Collection;

public interface View<T> {
    void present(T object);
    void present(Collection<T> objects);
}
