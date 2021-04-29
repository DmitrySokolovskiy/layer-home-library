package by.it.academia.library.service;

import by.it.academia.library.service.exception.ServiceException;
import by.it.academia.library.service.exception.ServiceNotFoundException;

public interface ClientService {
    void signIn(String login, String password) throws ServiceException, ServiceNotFoundException;
}
