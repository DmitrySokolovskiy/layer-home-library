package by.it.academia.library.controller.command.impl;

import by.it.academia.library.bean.Book;
import by.it.academia.library.controller.command.Command;
import by.it.academia.library.service.LibraryService;
import by.it.academia.library.service.exception.ServiceException;
import by.it.academia.library.service.exception.ServiceNoPermissionsException;
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
            int id = Integer.parseInt(parsedRequest[1].trim());
            String login = parsedRequest[2].trim();

            Book book = new Book(id);

            if (parsedRequest.length != 1) {
                libraryService.deleteBook(book, login);
                response = "Книга была удалена";
            }
        } catch (ServiceException e) {
            response = "Упс, что-то пошло не так";
        } catch (ServiceNotFoundException e) {
            response = "Книга отсутствует в библиотеке";
        } catch (
                ServiceNoPermissionsException e) {
            response = "У вас не достаточно прав для операции";
        }
        return response;
    }
}
