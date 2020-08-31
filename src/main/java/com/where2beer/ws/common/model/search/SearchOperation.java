package com.where2beer.ws.common.model.search;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SearchOperation {
    IN("|"),
    BETWEEN(":"),
    LIKE("~");

    private final String code;

    @Override
    public String toString() {
        return this.code;
    }

    public static SearchOperation fromString(String value) {
        for (SearchOperation operation : SearchOperation.values()) {
            if (operation.toString().equals(value)) {
                return operation;
            }
        }

        throw new IllegalArgumentException();
    }
}
