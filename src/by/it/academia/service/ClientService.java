package by.it.academia.service;

import by.it.academia.bean.User;

public interface ClientService {
    void signIn(String login, String password);
    void signOut(String login);
    void registration(User user);
}
