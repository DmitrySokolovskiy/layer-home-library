package by.it.academia.library.controller.command.impl;

import by.it.academia.library.controller.command.Command;
import by.it.academia.library.dao.exception.DAOException;
import by.it.academia.library.dao.exception.DAOResourceNotFoundException;
import by.it.academia.library.service.ClientService;
import by.it.academia.library.service.exception.ServiceException;
import by.it.academia.library.service.exception.ServiceNotFoundException;
import by.it.academia.library.service.factory.ServiceFactory;

import java.util.Map;

public class SignOut implements Command {

    private final String paramDelimeter = "#";

    @Override
    public String executeCommand(String request) {
        String[] parsedRequest = request.split(paramDelimeter);

        String login = parsedRequest[1];

        String response;
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ClientService clientService = serviceFactory.getClientService();

        try {
            clientService.signOut(login);
            response = "Вы разлогированны";
        } catch (ServiceException e) {
            response = "Что-то пошло не так";
        } catch (ServiceNotFoundException e) {
            response = "Пользователь не залогинен";
            response = null;
        }
        return response;
    }
}
