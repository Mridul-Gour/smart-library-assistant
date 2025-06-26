package model;

public class Book {
    private final String bookId;
    private final String title;
    private final String author;
    private boolean isAvailable;

    public Book(String bookId, String title, String author, boolean isAvailable) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isAvailable = isAvailable;
    }

    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    @Override
    public String toString() {
        return "Book { " +
               "bookId='" + bookId + '\'' +
               ", title='" + title + '\'' +
               ", author='" + author + '\'' +
               ", available=" + isAvailable +
               " }";
    }
}
