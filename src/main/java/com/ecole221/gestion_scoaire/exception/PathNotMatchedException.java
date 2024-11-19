package com.ecole221.gestion_scoaire.exception;

public class PathNotMatchedException extends RuntimeException {
    public PathNotMatchedException(String message) {
        super(message);
    }
}