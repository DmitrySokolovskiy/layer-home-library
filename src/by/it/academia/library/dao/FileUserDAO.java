package by.it.academia.library.dao;

import by.it.academia.library.bean.User;
import by.it.academia.library.dao.exception.DAOException;
import by.it.academia.library.dao.exception.DAOResourceExistsExeption;
import by.it.academia.library.dao.exception.DAOResourceNotFoundException;

public interface FileUserDAO {
    public void signIn(String login, String password) throws DAOException, DAOResourceNotFoundException;
    public void registration(User user) throws DAOException, DAOResourceExistsExeption;
    public boolean checkPermissions(String login) throws DAOException;
}
