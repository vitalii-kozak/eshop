package ua.kozak_vitalii.project_9.exceptions;

public class WrongUserDataException extends Exception {
    public WrongUserDataException() {
        super();
    }

    public WrongUserDataException(String message) {
        super(message);
    }
}
