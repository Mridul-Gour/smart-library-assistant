package dao;

import config.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.BookEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BookDAO {
    private static final Logger logger = LoggerFactory.getLogger(BookDAO.class);

    // INSERT new book
    public boolean addBook(BookEntity book) {
        logger.debug("Preparing to add book: {}", book.getTitle());
        logger.info("Adding book: {}", book.getTitle());
        String sql = "INSERT INTO books (book_id, title, author, publisher, publication_year, total_copies, copies_available) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, book.getBookId());
            stmt.setString(2, book.getTitle());
            stmt.setString(3, book.getAuthor());
            stmt.setString(4, book.getPublisher());
            stmt.setInt(5, book.getPublicationYear());
            stmt.setInt(6, book.getTotalCopies());
            stmt.setInt(7, book.getCopiesAvailable());

            int rowsInserted = stmt.executeUpdate();
            logger.info("Book insert executed, rowsInserted={}", rowsInserted);
            if (rowsInserted == 0) {
                logger.warn("No book was inserted for title: {}", book.getTitle());
            }
            return rowsInserted > 0;
        } catch (SQLTimeoutException e) {
            logger.warn("Timeout while adding book: {}", book.getTitle(), e);
            return false;
        } catch (SQLException e) {
            logger.error("Error adding book: {}", book.getTitle(), e);
            return false;
        }
    }

    // SEARCH book by title
    public List<BookEntity> getBookByTitle(String title) {
        logger.debug("Searching books by title: {}", title);
        logger.info("Searching books by title: {}", title);
        String sql = "SELECT * FROM books WHERE LOWER(title) LIKE ?";
        List<BookEntity> books = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + title.toLowerCase() + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                BookEntity book = new BookEntity();
                book.setBookId(rs.getInt("book_id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setPublisher(rs.getString("publisher"));
                book.setPublicationYear(rs.getInt("publication_year"));
                book.setTotalCopies(rs.getInt("total_copies"));
                book.setCopiesAvailable(rs.getInt("copies_available"));

                books.add(book);
            }
            logger.info("Found {} books for title search: {}", books.size(), title);
            if (books.isEmpty()) {
                logger.warn("No books found for title search: {}", title);
            }
        } catch (SQLException e) {
            logger.error("Error searching books by title: {}", title, e);
        }
        return books;
    }

    // UPDATE book details by book_id
    public boolean updateBook(BookEntity book) {
        logger.debug("Preparing to update book: {}", book.getTitle());
        logger.info("Updating book: {}", book.getTitle());
        String sql = "UPDATE books SET title = ?, author = ?, publisher = ?, publication_year = ?, total_copies = ?, copies_available = ? WHERE book_id = ?";

        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getPublisher());
            stmt.setInt(4, book.getPublicationYear());
            stmt.setInt(5, book.getTotalCopies());
            stmt.setInt(6, book.getCopiesAvailable());
            stmt.setInt(7, book.getBookId());

            int rowsUpdated = stmt.executeUpdate();
            logger.info("Book update executed, rowsUpdated={}", rowsUpdated);
            if (rowsUpdated == 0) {
                logger.warn("No book was updated for title: {}", book.getTitle());
            }
            return rowsUpdated > 0;
        } catch (SQLTimeoutException e) {
            logger.warn("Timeout while updating book: {}", book.getTitle(), e);
            return false;
        } catch (SQLException e) {
            logger.error("Error updating book: {}", book.getTitle(), e);
            return false;
        }
    }

    // DELETE book by book_id
    public boolean deleteBook(int bookId) {
        logger.debug("Preparing to delete book with ID: {}", bookId);
        logger.info("Deleting book with ID: {}", bookId);
        String sql = "DELETE FROM books WHERE book_id = ?";

        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, bookId);
            int rowsDeleted = stmt.executeUpdate();
            logger.info("Book delete executed, rowsDeleted={}", rowsDeleted);
            if (rowsDeleted == 0) {
                logger.warn("No book was deleted for ID: {}", bookId);
            }
            return rowsDeleted > 0;
        } catch (SQLTimeoutException e) {
            logger.warn("Timeout while deleting book with ID: {}", bookId, e);
            return false;
        } catch (SQLException e) {
            logger.error("Error deleting book with ID: {}", bookId, e);
            return false;
        }
    }

}
