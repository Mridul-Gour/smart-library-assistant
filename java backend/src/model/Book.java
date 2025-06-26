package model;

public sealed interface Book permits Book.TextBook, Book.Novel {
    String getTitle();
    String getAuthor();
    String getISBN();

    record TextBook(String title, String author, String isbn, String subject) implements Book {
        @Override
        public String getTitle() {
            return title;
        }

        @Override
        public String getAuthor() {
            return author;
        }

        @Override
        public String getISBN() {
            return isbn;
        }
    }

    record Novel(String title, String author, String isbn, String genre) implements Book {
        @Override
        public String getTitle() {
            return title;
        }

        @Override
        public String getAuthor() {
            return author;
        }

        @Override
        public String getISBN() {
            return isbn;
        }
    }
}
