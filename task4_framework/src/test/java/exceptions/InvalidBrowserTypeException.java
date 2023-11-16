package exceptions;

public class InvalidBrowserTypeException extends RuntimeException {

    public InvalidBrowserTypeException(String message) {
        super(message);
    }
}
