package com.jell.learning.ibeer.domain.beer;

public class BeerNameDuplicatedException extends RuntimeException {

    public BeerNameDuplicatedException(String message) {
        super(message);
    }
}
