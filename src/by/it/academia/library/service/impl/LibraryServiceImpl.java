package by.it.academia.library.service.impl;

import by.it.academia.library.bean.Book;
import by.it.academia.library.dao.exception.DAOException;
import by.it.academia.library.dao.exception.DAOResourceExistsExeption;
import by.it.academia.library.dao.exception.DAOResourceNotFoundException;
import by.it.academia.library.dao.factory.DAOFactory;
import by.it.academia.library.service.LibraryService;
import by.it.academia.library.service.exception.ServiceAlreadyExistException;
import by.it.academia.library.service.exception.ServiceException;
import by.it.academia.library.service.exception.ServiceNotFoundException;

public class LibraryServiceImpl implements LibraryService {
    @Override
    public String PrintBookList() throws ServiceNotFoundException, ServiceException {
        String response;
        try {
            response = DAOFactory.getInstance().getFileBookDAO().PrintBookList();
        }catch (DAOResourceNotFoundException e){
            throw new ServiceNotFoundException(e);
        }catch (DAOException e){
            throw new ServiceException(e);
    }

        return response;
    }

    @Override
    public void addNewBook(Book book) throws ServiceException, ServiceAlreadyExistException {
        try {
            DAOFactory.getInstance().getFileBookDAO().addBook(book);
        }catch (DAOException e){
            throw new ServiceException(e);
        }catch (DAOResourceExistsExeption e){
            throw new ServiceAlreadyExistException("Книга уже добавлена в библиотеку", e);
        }
    }

    @Override
    public void deleteBook(Book book) throws ServiceException, ServiceNotFoundException {
        try {
            DAOFactory.getInstance().getFileBookDAO().deleteBook(book);
        }catch (DAOException e){
            throw new ServiceException(e);
        }catch (DAOResourceNotFoundException e){
            throw new ServiceNotFoundException("Книга отсутствует в библиотеке", e);
        }
    }


}
