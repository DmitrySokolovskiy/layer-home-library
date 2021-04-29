package by.it.academia.library.dao.impl;

import by.it.academia.library.dao.FileUserDAO;
import by.it.academia.library.dao.exception.DAOException;
import by.it.academia.library.dao.exception.DAOResourceNotFoundException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UserDAO implements FileUserDAO {
    private final String fileName = "User.txt";
    private final String delimeter = "#";

    @Override
    public void signIn(String login, String password) throws DAOException, DAOResourceNotFoundException {
        String filePath = String.format(".\\resources\\%s", fileName);
        Path path = Paths.get(filePath);
            if (Files.exists(path)){
                try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if(line.split(delimeter)[0].equals(login) && line.split(delimeter)[1].equals(password)){
                            return;
                        }
                    }
                    throw new DAOResourceNotFoundException("Пользователь не найден");
                }catch (IOException e){
                    throw new DAOException("Не удалось открыть файл на чтение", e);
                } catch (DAOResourceNotFoundException e) {
                    throw new DAOResourceNotFoundException(e);
                }
            }
    }
}
