package com.itvdn.springdata.webdemodiary.exception;

public class CityAlreadyExistsException extends RuntimeException {
    public CityAlreadyExistsException(String cityName) {
        super("City '" + cityName + "' already exists");
    }
}
