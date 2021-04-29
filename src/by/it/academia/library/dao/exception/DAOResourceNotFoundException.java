package by.it.academia.library.dao.exception;

public class DAOResourceNotFoundException extends Exception {
    public DAOResourceNotFoundException() {
    }

    public DAOResourceNotFoundException(String message) {
        super(message);
    }

    public DAOResourceNotFoundException(Exception cause) {
        super(cause);
    }

    public DAOResourceNotFoundException(String message, Exception cause) {
        super(message, cause);
    }
}
