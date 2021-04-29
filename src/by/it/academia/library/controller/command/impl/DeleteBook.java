package by.it.academia.library.controller.command.impl;

import by.it.academia.library.bean.Book;
import by.it.academia.library.controller.command.Command;
import by.it.academia.library.service.LibraryService;
import by.it.academia.library.service.exception.ServiceException;
import by.it.academia.library.service.exception.ServiceNotFoundException;
import by.it.academia.library.service.factory.ServiceFactory;

public class DeleteBook implements Command {

    private final String paramDelimeter = "#";

    @Override
    public String executeCommand(String request) {

        String response = "";

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        LibraryService libraryService = serviceFactory.getLibraryService();

        try {
            String[] parsedRequest = request.split(paramDelimeter);
            int uid = Integer.parseInt(parsedRequest[1]);
            String title = parsedRequest[2];
            String author = parsedRequest[3];
            int publicationYear = Integer.parseInt(parsedRequest[4]);
            int bookLength = Integer.parseInt(parsedRequest[5]);
            String genre = parsedRequest[6];


            Book book = new Book(uid, title, author, publicationYear, bookLength, genre);

            if (parsedRequest.length != 1) {
                libraryService.deleteBook(book);
                response = "Книга была удалена";
            }
        } catch (ServiceException e) {
            response = "Упс, что-то пошло не так";
        } catch (ServiceNotFoundException e) {
            response = "Книга отсутствует в библиотеке";
        }
        return response;
    }
}
