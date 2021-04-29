package by.it.academia.library.service;

import by.it.academia.library.bean.Book;
import by.it.academia.library.service.exception.ServiceAlreadyExistException;
import by.it.academia.library.service.exception.ServiceException;
import by.it.academia.library.service.exception.ServiceNotFoundException;

public interface LibraryService {
    String PrintBookList() throws ServiceNotFoundException, ServiceException;
    void addNewBook(Book book) throws ServiceException, ServiceAlreadyExistException;
    void deleteBook(Book book) throws ServiceException, ServiceNotFoundException;
}
