package by.it.academia.library.controller.command.impl;

import by.it.academia.library.controller.command.Command;

public class WrongRequest implements Command {

    private final String paramDelimeter = "#";

    @Override
    public String executeCommand(String request) {
        return null;
    }
}
