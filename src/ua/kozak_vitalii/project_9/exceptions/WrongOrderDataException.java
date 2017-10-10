package ua.kozak_vitalii.project_9.exceptions;

public class WrongOrderDataException extends Exception {
    public WrongOrderDataException() {
        super();
    }

    public WrongOrderDataException(String message) {
        super(message);
    }
}
