package by.it.academia.library.dao.exception;

public class DAOResourceExistsExeption extends Exception {
    public DAOResourceExistsExeption() {
    }

    public DAOResourceExistsExeption(String message) {
        super(message);
    }

    public DAOResourceExistsExeption(String message, Exception cause) {
        super(message, cause);
    }

    public DAOResourceExistsExeption(Exception cause) {
        super(cause);
    }
}
