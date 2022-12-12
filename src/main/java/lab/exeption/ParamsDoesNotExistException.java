package lab.exeption;

public class ParamsDoesNotExistException extends RuntimeException {

    public ParamsDoesNotExistException(String message) {
        super(message);
    }
}

