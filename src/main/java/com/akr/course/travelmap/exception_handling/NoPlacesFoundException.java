package com.akr.course.travelmap.exception_handling;

public class NoPlacesFoundException extends RuntimeException{
    public NoPlacesFoundException(String message) {
        super(message);
    }
}
