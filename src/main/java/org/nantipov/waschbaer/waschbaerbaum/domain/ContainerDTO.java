package org.nantipov.waschbaer.waschbaerbaum.domain;

import lombok.Getter;

import java.util.Collection;

@Getter
public class ContainerDTO<T> {
    private Collection<T> items;

    private ContainerDTO(Collection<T> items) {
        this.items = items;
    }

    public static <T> ContainerDTO<T> of(Collection<T> items) {
        return new ContainerDTO<>(items);
    }
}
