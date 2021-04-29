package by.it.academia.library.dao;

import by.it.academia.library.bean.User;
import by.it.academia.library.dao.exception.DAOException;
import by.it.academia.library.dao.exception.DAOResourceNotFoundException;

import java.io.IOException;

public interface FileUserDAO {
    public void signIn(String login, String password) throws DAOException, DAOResourceNotFoundException;
    public void signOut(String login) throws DAOException, DAOResourceNotFoundException;
    public void registration(User user) throws IOException, DAOException, DAOResourceNotFoundException;
}
