package by.it.academia.library.dao;

import by.it.academia.library.bean.Book;
import by.it.academia.library.dao.exception.DAOException;
import by.it.academia.library.dao.exception.DAOResourceExistsExeption;
import by.it.academia.library.dao.exception.DAOResourceNotFoundException;

public interface FileBookDAO {
    public String PrintBookList() throws DAOException, DAOResourceNotFoundException;
    public void addBook(Book book) throws DAOException, DAOResourceExistsExeption;
    public void deleteBook(Book book) throws DAOException, DAOResourceNotFoundException;
}
