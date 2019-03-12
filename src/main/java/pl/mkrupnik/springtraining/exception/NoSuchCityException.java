package pl.mkrupnik.springtraining.exception;

public class NoSuchCityException extends Exception {
    public NoSuchCityException() {
    }
    public NoSuchCityException(String message) {
        super(message);
    }
}
