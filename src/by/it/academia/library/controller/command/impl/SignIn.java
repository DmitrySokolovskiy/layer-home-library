package by.it.academia.library.controller.command.impl;

import by.it.academia.library.controller.command.Command;
import by.it.academia.library.service.ClientService;
import by.it.academia.library.service.exception.ServiceException;
import by.it.academia.library.service.exception.ServiceNotFoundException;
import by.it.academia.library.service.factory.ServiceFactory;


public class SignIn implements Command {

    private final String paramDelimeter = "#";

    @Override
    public String executeCommand(String request) {
        String[] parsedRequest = request.split(paramDelimeter);

        String login = null;
        String password = null;

        String response;

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ClientService clientService = serviceFactory.getClientService();

        try {
            login = parsedRequest[1];
            password = parsedRequest[2];
            clientService.signIn(login, password);
            response = "Welcome!!!";
        } catch (ServiceException e) {
            response = "Что-то пошло не так";
        } catch (ServiceNotFoundException e) {
            response = "Пользователь не был найден в списке, зарегистрируйтесь!!!";
        } catch (ArrayIndexOutOfBoundsException e) {
            response = "Введите логин и пароль";
        }
        return response;
    }
}
