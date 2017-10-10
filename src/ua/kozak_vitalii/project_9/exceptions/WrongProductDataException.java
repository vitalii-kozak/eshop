package ua.kozak_vitalii.project_9.exceptions;

public class WrongProductDataException extends Exception {

    public WrongProductDataException() {
        super();
    }

    public WrongProductDataException(String message) {
        super(message);
    }
}
