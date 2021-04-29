package by.it.academia.library.service.impl;

import by.it.academia.library.bean.User;
import by.it.academia.library.dao.exception.DAOException;
import by.it.academia.library.dao.exception.DAOResourceNotFoundException;
import by.it.academia.library.dao.factory.DAOFactory;
import by.it.academia.library.service.ClientService;
import by.it.academia.library.service.exception.ServiceException;
import by.it.academia.library.service.exception.ServiceNotFoundException;

import java.io.IOException;


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

    @Override
    public void signOut(String login) throws ServiceException, ServiceNotFoundException {
        try {
            DAOFactory.getInstance().getFileUserDAO().signOut(login);
        } catch (DAOResourceNotFoundException e){
            throw new ServiceNotFoundException(e);
        }catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public void registration(User user) throws DAOResourceNotFoundException, ServiceException {
        try {
            DAOFactory.getInstance().getFileUserDAO().registration(user);
        } catch (DAOResourceNotFoundException e){
            throw new DAOResourceNotFoundException("Пользователь не залогинен");
        }catch (DAOException e){
            throw new ServiceException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
