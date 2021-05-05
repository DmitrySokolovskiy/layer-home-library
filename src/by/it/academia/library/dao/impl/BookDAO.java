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
    private final String fileName = "Book.txt";
    private final String delimeter = "#";

    @Override
    public String PrintBookList() throws DAOException {
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
                    books = "Наша библиотека была разорена:) или книги уехали в другую библиотеку";
                }
            } catch (IOException e) {
                throw new DAOException("Не удалось открыть файл на чтение", e);
            }
        }
        return books;
    }

    @Override
    public void addBook(Book book) throws DAOException, DAOResourceExistsExeption {

        String filePath = String.format(".\\resources\\%s", fileName);
        book.setId(bookIdNumberGenerator());

        try {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (!line.trim().equals("")) {
                        String[] splitedLine = line.split(delimeter);
                        if (splitedLine[1].equals(book.getTitle())) {
                            throw new DAOResourceExistsExeption("Книга уже есть библиотеке");
                        }
                    }
                }
            }
            try (FileWriter fr = new FileWriter(filePath, true)) {
                fr.write(String.format("%d#%s#%s#%d#%d#%s\n", book.getId(), book.getTitle(),
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
                        if (Integer.parseInt(splitedLine[0]) != book.getId()) {
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

    private int bookIdNumberGenerator() throws DAOException {

        String filePath = String.format(".\\resources\\%s", fileName);
        int generatedBookId;

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
                generatedBookId = Integer.parseInt(splitedLine[0]) + 1;
            }
        } catch (IOException e) {
            throw new DAOException(e);
        } catch (NumberFormatException e) {
            throw new DAOException("Нарушена структура файла", e);
        }

        return generatedBookId;
    }

    @Override
    public String getBookDetailInfo(int bookId) throws DAOException {
        String filePath = String.format(".\\resources\\%s", fileName);

        try {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (!line.trim().equals("")) {
                        String[] splitedLine = line.split(delimeter);
                        if (Integer.parseInt(splitedLine[0]) == bookId) {
                            return String.format("%d %s %s %s %s %s", bookId, splitedLine[1], splitedLine[2],
                                    splitedLine[3], splitedLine[4], splitedLine[5]);
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new DAOException(e);
        } catch (NumberFormatException e) {
            throw new DAOException("Нарушена структура файла", e);
        }

        return "Книга с таким номером не найдена в библиотеке";
    }
}
