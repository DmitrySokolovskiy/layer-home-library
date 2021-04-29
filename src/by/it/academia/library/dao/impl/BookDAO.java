package by.it.academia.library.dao.impl;

import by.it.academia.library.bean.Book;
import by.it.academia.library.dao.FileBookDAO;
import by.it.academia.library.dao.exception.DAOException;
import by.it.academia.library.dao.exception.DAOResourceExistsExeption;
import by.it.academia.library.dao.exception.DAOResourceNotFoundException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class BookDAO implements FileBookDAO {
    private final String fileName = "newFile2.txt";
    private final String delimeter = "#";

    @Override
    public String PrintBookList() throws DAOException, DAOResourceNotFoundException {
        String filePath = String.format(".\\resources\\%s", fileName);
        Path path = Paths.get(filePath);
        String books = "";
        if (Files.exists(path)) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

                String line;

                while ((line = reader.readLine()) != null) {
                    if (!line.trim().equals("")) {
                        String[] splitedLine = line.split(delimeter);
                        books += String.format("%s %s %s \n", splitedLine[0], splitedLine[1], splitedLine[2]);
                    }
                }
                if (books == "") {
                    throw new DAOResourceNotFoundException("Книг в библиотеке нет");
                }
            } catch (IOException e) {
                throw new DAOException("Не удалось открыть файл на чтение", e);
            } catch (DAOResourceNotFoundException e) {
                throw new DAOResourceNotFoundException(e);
            }
        }
        return books;
    }

    @Override
    public void addBook(Book book) throws DAOException, DAOResourceExistsExeption {

        String filePath = String.format(".\\resources\\%s", fileName);


        try {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (!line.trim().equals("")) {
                        String[] splitedLine = line.split(delimeter);
                        if (Integer.parseInt(splitedLine[0]) == book.getSerialNumber() || splitedLine[1].equals(book.getTitle())) {
                            throw new DAOResourceExistsExeption("Книга уже есть библиотеке");
                        }
                    }
                }
            }
            try (FileWriter fr = new FileWriter(filePath, true)) {
                fr.write(String.format("%d#%s#%s#%d#%d#%s\n", book.getSerialNumber(), book.getTitle(),
                        book.getAuthor(), book.getPublicationYear(), book.getBookLength(), book.getGenre()));
            }
        } catch (IOException e) {
            throw new DAOException(e);
        } catch (NumberFormatException e) {
            throw new DAOException("Нарушена структура файла", e);
        }
    }

    @Override
    public void deleteBook(Book book) throws DAOException, DAOResourceNotFoundException {

        String filePath = String.format(".\\resources\\%s", fileName);
        List<String> books = new ArrayList<>();
        boolean notFound = true;
        try {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (!line.trim().equals("")) {
                        String[] splitedLine = line.split(delimeter);
                        if (Integer.parseInt(splitedLine[0]) != book.getSerialNumber() & !splitedLine[1].equals(book.getTitle())) {
                            books.add(line);
                        } else {
                            notFound = false;
                        }
                    }
                }
                if (notFound) {
                    throw new DAOResourceNotFoundException("Книга не найдена в библиотеке");
                }
            }
            try (FileWriter fr = new FileWriter(filePath, false)) {
                for (String b : books) {
                    fr.write(b + "\n");
                }
            }
        } catch (IOException e) {
            throw new DAOException(e);
        } catch (NumberFormatException e) {
            throw new DAOException("Нарушена структура файла", e);
        }
    }
}
