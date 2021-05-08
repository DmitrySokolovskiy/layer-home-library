package by.it.academia.library.dao;

import by.it.academia.library.bean.Book;
import by.it.academia.library.dao.exception.DAOException;
import by.it.academia.library.dao.exception.DAOResourceExistsExeption;
import by.it.academia.library.dao.exception.DAOResourceNotFoundException;

import java.util.ArrayList;

public interface FileBookDAO {
    ArrayList<Book> PrintBookList() throws DAOException;
    void addBook(Book book) throws DAOException, DAOResourceExistsExeption;
    void deleteBook(Book book) throws DAOException, DAOResourceNotFoundException;
    Book getBookDetailInfo(int bookId) throws DAOException;
}
