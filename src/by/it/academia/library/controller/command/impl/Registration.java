package by.it.academia.library.controller.command.impl;

import by.it.academia.library.bean.User;
import by.it.academia.library.controller.command.Command;
import by.it.academia.library.dao.exception.DAOResourceNotFoundException;
import by.it.academia.library.service.ClientService;
import by.it.academia.library.service.exception.ServiceException;
import by.it.academia.library.service.factory.ServiceFactory;

public class Registration implements Command {

    private final String paramDelimeter = "#";

    @Override
    public String executeCommand(String request) {
        String response = null;
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        String[] parsedRequest = request.split(paramDelimeter);

        User user = new User(parsedRequest[1], parsedRequest[2]);
        ClientService clientService = serviceFactory.getClientService();

        try {
            clientService.registration(user);
            response = "Вы зарегистрированны!!!";
        } catch (ServiceException e) {
            response = "Oops что-то пошло не так";
        } catch (DAOResourceNotFoundException e) {
            e.printStackTrace();
        }

        return response;
    }
}
