package org.nantipov.waschbaer.waschbaerbaum.domain;

import java.util.Collection;

public class ContainerDTO<T> {
    private Collection<T> items;

    private ContainerDTO(Collection<T> items) {
        this.items = items;
    }

    public static <T> ContainerDTO<T> of(Collection<T> items) {
        return new ContainerDTO<>(items);
    }
}
