package by.it.academia.library.controller.command.impl;

import by.it.academia.library.controller.command.Command;
import by.it.academia.library.service.LibraryService;
import by.it.academia.library.service.exception.ServiceException;
import by.it.academia.library.service.exception.ServiceNotFoundException;
import by.it.academia.library.service.factory.ServiceFactory;

public class PrintBookList implements Command {

    private final String paramDelimeter = "#";


    @Override
    public String executeCommand(String request) {

        String[] parsedRequest = request.split(paramDelimeter);

        String response= "";

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        LibraryService libraryService = serviceFactory.getLibraryService();

        try {
            if (parsedRequest.length == 1){
                response = libraryService.PrintBookList();
            }
        }catch (ServiceException e){
            response = "Упс, что-то пошло не так";
        }catch (ServiceNotFoundException e){
            response = "В библиотеке нет книг";
        }
        return response;
    }
}
