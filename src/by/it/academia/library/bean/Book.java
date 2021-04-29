package by.it.academia.library.bean;

import java.io.Serializable;

public class Book implements Serializable {
    private int id;
    private String title;
    private String author;
    private int bookLength;
    private String genre;
    private int publicationYear;
    private int serialNumber;

    public Book(int serialNumber, String title, String author,int publicationYear, int bookLength, String genre) {
        this.title = title;
        this.author = author;
        this.bookLength = bookLength;
        this.genre = genre;
        this.publicationYear = publicationYear;
        this.serialNumber = serialNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getBookLength() {
        return bookLength;
    }

    public void setBookLength(int bookLength) {
        this.bookLength = bookLength;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }
}
