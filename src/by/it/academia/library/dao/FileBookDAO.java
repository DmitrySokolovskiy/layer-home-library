package by.it.academia.library.dao;

import by.it.academia.library.bean.Book;
import by.it.academia.library.dao.exception.DAOException;
import by.it.academia.library.dao.exception.DAOResourceExistsExeption;
import by.it.academia.library.dao.exception.DAOResourceNotFoundException;

public interface FileBookDAO {
    String PrintBookList() throws DAOException, DAOResourceNotFoundException;
    void addBook(Book book) throws DAOException, DAOResourceExistsExeption;
    void deleteBook(Book book) throws DAOException, DAOResourceNotFoundException;
}
