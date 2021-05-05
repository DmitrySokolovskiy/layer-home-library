package by.it.academia.library.bean;

import java.io.Serializable;
import java.util.Objects;

public class Book implements Serializable {
    private int id;
    private String title;
    private String author;
    private int bookLength;
    private String genre;
    private int publicationYear;

    public Book(int id){
        this.id = id;
    }
    public Book(String title, String author, int publicationYear, int bookLength, String genre) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.bookLength = bookLength;
        this.genre = genre;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id &&
                bookLength == book.bookLength &&
                publicationYear == book.publicationYear &&
                title.equals(book.title) &&
                author.equals(book.author) &&
                genre.equals(book.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, bookLength, genre, publicationYear);
    }

    @Override
    public String toString() {
        return getClass().getName() + "{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", bookLength=" + bookLength +
                ", genre='" + genre + '\'' +
                ", publicationYear=" + publicationYear +
                '}';
    }
}
