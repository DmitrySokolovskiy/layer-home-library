package by.it.academia.library.service;

import by.it.academia.library.bean.Book;
import by.it.academia.library.dao.exception.DAOException;
import by.it.academia.library.service.exception.ServiceAlreadyExistException;
import by.it.academia.library.service.exception.ServiceException;
import by.it.academia.library.service.exception.ServiceNoPermissionsException;
import by.it.academia.library.service.exception.ServiceNotFoundException;

import java.util.ArrayList;

public interface LibraryService {
    ArrayList<Book> PrintBookList() throws ServiceNotFoundException, ServiceException;
    void addNewBook(Book book, String login) throws ServiceException, ServiceAlreadyExistException, ServiceNoPermissionsException;
    void deleteBook(Book book, String login) throws ServiceException, ServiceNotFoundException, ServiceNoPermissionsException;
    Book getBookDetailInfo(int bookId) throws ServiceException, ServiceNotFoundException;
}
