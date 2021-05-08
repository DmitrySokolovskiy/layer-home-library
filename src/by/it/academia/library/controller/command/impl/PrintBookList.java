package by.it.academia.library.controller.command.impl;

import by.it.academia.library.bean.Book;
import by.it.academia.library.controller.command.Command;
import by.it.academia.library.service.LibraryService;
import by.it.academia.library.service.exception.ServiceException;
import by.it.academia.library.service.exception.ServiceNotFoundException;
import by.it.academia.library.service.factory.ServiceFactory;

import java.util.ArrayList;

public class PrintBookList implements Command {

    //private final String paramDelimeter = "#";


    @Override
    public String executeCommand(String request) {

        ArrayList<Book> serviceResponse;
        String response = "";

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        LibraryService libraryService = serviceFactory.getLibraryService();

        try {
            serviceResponse = libraryService.PrintBookList();
            for (Book book : serviceResponse) {
                response += String.format("%d %s %s %d %d %s\n",
                        book.getId(),
                        book.getTitle(),
                        book.getAuthor(),
                        book.getPublicationYear(),
                        book.getBookLength(),
                        book.getGenre()
                );
            }
        } catch (ServiceException e) {
            response = "Упс, что-то пошло не так";
        } catch (ServiceNotFoundException e) {
            response = "В библиотеке нет книг";
        }
        return response;
    }
}
