package pl.mkrupnik.springtraining.service;

public class NoSuchCityException extends Exception {
    public NoSuchCityException() {
    }
    public NoSuchCityException(String message) {
        super(message);
    }
}
