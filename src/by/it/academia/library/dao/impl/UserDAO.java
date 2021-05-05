package by.it.academia.library.dao.impl;

import by.it.academia.library.bean.User;
import by.it.academia.library.dao.FileUserDAO;
import by.it.academia.library.dao.exception.DAOException;
import by.it.academia.library.dao.exception.DAOResourceExistsExeption;
import by.it.academia.library.dao.exception.DAOResourceNotFoundException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements FileUserDAO {
    private final String fileName = "User.txt";
    private final String delimeter = "#";

    @Override
    public void signIn(String login, String password) throws DAOException, DAOResourceNotFoundException {
        String filePath = String.format(".\\resources\\%s", fileName);
        Path path = Paths.get(filePath);
        if (Files.exists(path)) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.split(delimeter)[1].equals(login) && line.split(delimeter)[2].equals(password)) {
                        return;
                    }
                }
                throw new DAOResourceNotFoundException("Пользователь не найден");
            } catch (IOException e) {
                throw new DAOException("Не удалось открыть файл на чтение", e);
            } catch (DAOResourceNotFoundException e) {
                throw new DAOResourceNotFoundException(e);
            }
        }
    }

    @Override
    public void registration(User user) throws DAOException, DAOResourceExistsExeption {
        String filePath = String.format(".\\resources\\%s", fileName);
        List<String> users = new ArrayList<>();

        user.setId(userIdNumberGenerator());
        try {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (!line.trim().equals("")) {
                        String[] splitedLine = line.split(delimeter);
                        if (!splitedLine[1].equals(user.getLogin())) {
                            users.add(line);
                        } else {
                            throw new DAOResourceExistsExeption("Пользователь уже заригистрирован");
                        }
                    }
                }
                String convertToString = String.format("%d#%s#%s#%s#User", user.getId(), user.getLogin(),
                        user.getPassword(), user.getEmail());
                users.add(convertToString);
            }
            try (FileWriter fr = new FileWriter(filePath, false)) {
                for (String b : users) {
                    fr.write(b + "\n");
                }
            }
        } catch (IOException e) {
            throw new DAOException(e);
        } catch (NumberFormatException e) {
            throw new DAOException("Нарушена структура файла", e);
        }
    }


    private int userIdNumberGenerator() throws DAOException {

        String filePath = String.format(".\\resources\\%s", fileName);
        int generatedUserId;

        try {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                String temp = "";
                while ((line = reader.readLine()) != null) {
                    if (!line.trim().equals("")) {
                        temp = line;
                    }
                }
                String[] splitedLine = temp.split(delimeter);
                generatedUserId = Integer.parseInt(splitedLine[0]) + 1;
            }
        } catch (IOException e) {
            throw new DAOException(e);
        } catch (NumberFormatException e) {
            throw new DAOException("Нарушена структура файла", e);
        }

        return generatedUserId;
    }

    @Override
    public boolean checkPermissions(String login) throws DAOException {
        String filePath = String.format(".\\resources\\%s", fileName);

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().equals("")) {
                    String[] splitedLine = line.split(delimeter);
                    if (splitedLine[1].equals(login.trim()) && splitedLine[4].equals("Administrator")) {
                        return true;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new DAOException(e);
        } catch (IOException e) {
            throw new DAOException(e);
        }
        return false;
    }
}