package by.it.academia.library.service.impl;

import by.it.academia.library.dao.exception.DAOException;
import by.it.academia.library.dao.exception.DAOResourceNotFoundException;
import by.it.academia.library.dao.factory.DAOFactory;
import by.it.academia.library.service.ClientService;
import by.it.academia.library.service.exception.ServiceException;
import by.it.academia.library.service.exception.ServiceNotFoundException;



public class ClientServiceImpl implements ClientService {

    @Override
    public void signIn(String login, String password) throws ServiceException, ServiceNotFoundException {
        try {
            DAOFactory.getInstance().getFileUserDAO().signIn(login, password);
        }catch (DAOException e) {
            throw new ServiceException(e);
        }catch (DAOResourceNotFoundException e){
            throw new ServiceNotFoundException("Пользователь не найден", e);
        }

    }
}
