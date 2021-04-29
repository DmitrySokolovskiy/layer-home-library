package by.it.academia.library.dao;

import by.it.academia.library.dao.exception.DAOException;
import by.it.academia.library.dao.exception.DAOResourceNotFoundException;

public interface FileUserDAO {
    public void signIn(String login, String password) throws DAOException, DAOResourceNotFoundException;
}
