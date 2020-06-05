package com.jell.learning.ibeer.sevice.beer.exception;

public class BeerNameDuplicatedException extends RuntimeException {

    public BeerNameDuplicatedException(String message) {
        super(message);
    }
}
