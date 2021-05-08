package by.it.academia.library.controller.command.impl;

import by.it.academia.library.bean.Book;
import by.it.academia.library.controller.command.Command;
import by.it.academia.library.service.LibraryService;
import by.it.academia.library.service.exception.ServiceException;
import by.it.academia.library.service.exception.ServiceNotFoundException;
import by.it.academia.library.service.factory.ServiceFactory;

public class getBookDetailInfo implements Command {

    private final String paramDelimeter = "#";

    @Override
    public String executeCommand(String request) {
        String response;

        try {
            String[] parsedRequest = request.split(paramDelimeter);
            int bookId = Integer.parseInt(parsedRequest[1]);

            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            LibraryService libraryService = serviceFactory.getLibraryService();

            Book serviceResponse = libraryService.getBookDetailInfo(bookId);
            response = String.format("%d %s %s %d %d %s",
                    serviceResponse.getId(),
                    serviceResponse.getTitle(),
                    serviceResponse.getAuthor(),
                    serviceResponse.getPublicationYear(),
                    serviceResponse.getBookLength(),
                    serviceResponse.getGenre()
            );


        } catch (ServiceException e) {
            response = "Упс, что-то пошло не так";
        } catch (NumberFormatException e) {
            response = "Не переданны все параметры для поиска книги";
        } catch (ServiceNotFoundException e) {
            response = "Такой книги нет в библотеке";
        }
        return response;
    }
}
