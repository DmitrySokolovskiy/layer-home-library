package by.it.academia.library.controller;

import by.it.academia.library.controller.command.Command;
import by.it.academia.library.controller.command.CommandName;
import by.it.academia.library.controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;

final class CommandProvider {
    private final Map<CommandName, Command> repository = new HashMap<>();

    public CommandProvider() {
        repository.put(CommandName.SIGN_IN, new SignIn());
        repository.put(CommandName.PRINT_BOOK_LIST, new PrintBookList());
        repository.put(CommandName.DELETE_BOOK, new DeleteBook());
        repository.put(CommandName.ADD_BOOK, new AddBook());
        repository.put(CommandName.WRONG_REQUEST, new WrongRequest());
    }

    Command getCommand(String name) {
        CommandName commandName;
        Command command;

        try {
            commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);
        }catch (IllegalArgumentException | NullPointerException e){
            command = repository.get(CommandName.WRONG_REQUEST);
        }
        return command;
    }

}
