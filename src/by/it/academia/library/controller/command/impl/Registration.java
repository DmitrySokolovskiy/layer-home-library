package by.it.academia.library.controller.command.impl;

import by.it.academia.library.bean.User;
import by.it.academia.library.controller.command.Command;
import by.it.academia.library.service.ClientService;
import by.it.academia.library.service.exception.ServiceAlreadyExistException;
import by.it.academia.library.service.exception.ServiceException;
import by.it.academia.library.service.factory.ServiceFactory;

public class Registration implements Command {

    private final String paramDelimeter = "#";

    @Override
    public String executeCommand(String request) {
        String response;

        try {
            String[] parsedRequest = request.split(paramDelimeter);
            String login = parsedRequest[1];
            String password = parsedRequest[2];
            String email = parsedRequest[3];

            User user = new User(login, password, email);

            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            ClientService clientService = serviceFactory.getClientService();

            clientService.registration(user);
            response = "Done!!!";

        } catch (ServiceException e) {
            response = "Упс, что-то пошло не так";
        } catch (ServiceAlreadyExistException e) {
            response = "Пользователь уже зарегистрирован в библиотеке";
        } catch (NumberFormatException e) {
            response = "Не переданны все параметры для регистрации";
        } catch (ArrayIndexOutOfBoundsException e){
            response = "Не корректно переданы параметры";
        }
        return response;
    }
}
