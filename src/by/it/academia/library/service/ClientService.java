package by.it.academia.library.service;

import by.it.academia.library.bean.User;
import by.it.academia.library.dao.exception.DAOResourceNotFoundException;
import by.it.academia.library.service.exception.ServiceException;
import by.it.academia.library.service.exception.ServiceNotFoundException;

public interface ClientService {
    void signIn(String login, String password) throws ServiceException, ServiceNotFoundException;
    void signOut(String login) throws ServiceException, ServiceNotFoundException;
    void registration(User user) throws DAOResourceNotFoundException, ServiceException;
}
