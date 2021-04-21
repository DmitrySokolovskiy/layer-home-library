package by.it.academia.dao.factory;

import by.it.academia.dao.FileBookDAO;
import by.it.academia.dao.FileUserDAO;
import by.it.academia.dao.impl.BookDAO;
import by.it.academia.dao.impl.UserDAO;

public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private final FileUserDAO fileUserDAO = new UserDAO();
    private final FileBookDAO fileBookDAO = new BookDAO();

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public FileUserDAO getFileUserDAO() {
        return fileUserDAO;
    }

    public FileBookDAO getFileBookDAO() {
        return fileBookDAO;
    }
}
