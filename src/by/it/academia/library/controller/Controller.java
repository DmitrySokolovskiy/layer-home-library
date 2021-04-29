package by.it.academia.library.controller;

import by.it.academia.library.controller.command.Command;


public final class Controller {
    public final CommandProvider provider = new CommandProvider();

    private final char paramDelimeter = '#';

    public String executeTask(String request){
        String commandName;
        Command executionCommand;
        String response;

        if (request.indexOf(paramDelimeter) == -1){
            request += paramDelimeter;
        }

        try {
            commandName = request.substring(0, request.indexOf(paramDelimeter));
            executionCommand = provider.getCommand(commandName);
            response = executionCommand.executeCommand(request);
        }catch (StringIndexOutOfBoundsException e){
            commandName = "WRONG_REQUEST";
            executionCommand = provider.getCommand(commandName);
            response = executionCommand.executeCommand(request);
        }
        return response;
    }
}
