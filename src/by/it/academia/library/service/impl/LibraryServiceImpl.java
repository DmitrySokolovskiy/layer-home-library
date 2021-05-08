package by.it.academia.library.service.impl;

import by.it.academia.library.bean.Book;
import by.it.academia.library.dao.exception.DAOException;
import by.it.academia.library.dao.exception.DAOResourceExistsExeption;
import by.it.academia.library.dao.exception.DAOResourceNotFoundException;
import by.it.academia.library.dao.factory.DAOFactory;
import by.it.academia.library.service.LibraryService;
import by.it.academia.library.service.exception.ServiceAlreadyExistException;
import by.it.academia.library.service.exception.ServiceException;
import by.it.academia.library.service.exception.ServiceNoPermissionsException;
import by.it.academia.library.service.exception.ServiceNotFoundException;

import java.util.ArrayList;

public class LibraryServiceImpl implements LibraryService {
    @Override
    public ArrayList<Book> PrintBookList() throws ServiceException {
        ArrayList<Book> response = new ArrayList<>();
        try {
            response.addAll(DAOFactory.getInstance().getFileBookDAO().PrintBookList());
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return response;
    }

    @Override
    public void addNewBook(Book book, String login) throws ServiceException, ServiceAlreadyExistException, ServiceNoPermissionsException {
        try {
            if (DAOFactory.getInstance().getFileUserDAO().checkPermissions(login)) {
                DAOFactory.getInstance().getFileBookDAO().addBook(book);
            } else {
                throw new ServiceNoPermissionsException("Операция требует администратоских прав!");
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        } catch (DAOResourceExistsExeption e) {
            throw new ServiceAlreadyExistException("Книга уже добавлена в библиотеку", e);
        } catch (ServiceNoPermissionsException e) {
            throw new ServiceNoPermissionsException(e);
        }
    }

    @Override
    public void deleteBook(Book book, String login) throws ServiceException, ServiceNotFoundException, ServiceNoPermissionsException {
        try {
            if (DAOFactory.getInstance().getFileUserDAO().checkPermissions(login)) {
                DAOFactory.getInstance().getFileBookDAO().deleteBook(book);
            } else {
                throw new ServiceNoPermissionsException("Операция требует администратоских прав!");
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        } catch (DAOResourceNotFoundException e) {
            throw new ServiceNotFoundException("Книга отсутствует в библиотеке", e);
        } catch (ServiceNoPermissionsException e) {
            throw new ServiceNoPermissionsException(e);
        }
    }

    @Override
    public Book getBookDetailInfo(int bookId) throws ServiceNotFoundException {
        Book response;
        try {
            response = DAOFactory.getInstance().getFileBookDAO().getBookDetailInfo(bookId);
        } catch (DAOException e) {
            throw new ServiceNotFoundException(e);
        }
        return response;
    }


}
