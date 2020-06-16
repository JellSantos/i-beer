package com.jell.learning.ibeer.domain.common;

public class NameDuplicatedException extends RuntimeException {

    public NameDuplicatedException(String message) {
        super(message);
    }
}
