package com.gabchak.exceptions;

public class ElementNotFoundExeption extends RuntimeException {

    @Override
    public String getMessage() {
        return "Element not found";
    }
}
