package by.it.academia.library.service.exception;

public class ServiceNoPermissionsException extends Exception {
    public ServiceNoPermissionsException() {
    }

    public ServiceNoPermissionsException(String message) {
        super(message);
    }

    public ServiceNoPermissionsException(String message, Exception e) {
        super(message, e);
    }

    public ServiceNoPermissionsException(Exception e) {
        super(e);
    }
}
