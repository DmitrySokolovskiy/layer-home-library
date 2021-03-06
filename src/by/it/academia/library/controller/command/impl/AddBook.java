package by.it.academia.library.controller.command.impl;

import by.it.academia.library.bean.Book;
import by.it.academia.library.controller.command.Command;
import by.it.academia.library.service.LibraryService;
import by.it.academia.library.service.exception.ServiceAlreadyExistException;
import by.it.academia.library.service.exception.ServiceException;
import by.it.academia.library.service.exception.ServiceNoPermissionsException;
import by.it.academia.library.service.factory.ServiceFactory;

public class AddBook implements Command {

    private final String paramDelimeter = "#";

    @Override
    public String executeCommand(String request) {
        String response;

        try {
            String[] parsedRequest = request.split(paramDelimeter);
            String title = parsedRequest[1];
            String author = parsedRequest[2];
            int publicationYear = Integer.parseInt(parsedRequest[3]);
            int bookLength = Integer.parseInt(parsedRequest[4]);
            String genre = parsedRequest[5];
            String login = parsedRequest[6];

            Book book = new Book(title, author, publicationYear, bookLength, genre);


            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            LibraryService libraryService = serviceFactory.getLibraryService();

            libraryService.addNewBook(book, login);
            response = "Книга была добавлена в библиотеку";

        } catch (ServiceException e) {
            response = "Упс, что-то пошло не так";
        } catch (ServiceAlreadyExistException e) {
            response = "Книга уже есть в библиотеке";
        } catch (NumberFormatException e) {
            response = "Не переданны все параметры для книги";
        } catch (ServiceNoPermissionsException e){
            response = "У вас не достаточно прав для операции";
        }
        return response;
    }
}
