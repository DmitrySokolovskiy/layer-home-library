package by.it.academia.library.service.exception;

public class ServiceAlreadyExistException extends Exception{
    public ServiceAlreadyExistException() {
    }

    public ServiceAlreadyExistException(String message) {
        super(message);
    }

    public ServiceAlreadyExistException(String message, Exception cause) {
        super(message, cause);
    }

    public ServiceAlreadyExistException(Exception cause) {
        super(cause);
    }

    public ServiceAlreadyExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
