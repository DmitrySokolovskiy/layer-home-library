package by.it.academia.dao;

import by.it.academia.bean.User;

public interface FileUserDAO {
    public void signIn(String login, String password);
    public void registration(User user);
}
