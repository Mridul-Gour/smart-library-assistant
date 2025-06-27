package app;

import java.util.List;
import java.util.Scanner;
import model.ReservationEntity;
import dao.BookDAO;
import dao.ReservationDAO;
import config.DBConnection;
import java.sql.Timestamp;
import model.BookEntity;

public class LibraryApp {
    public static void main(String[] args) {
        BookDAO dao = new BookDAO();
        ReservationDAO resDao = new ReservationDAO();
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Smart Library Assistant ===");
        System.out.println("1. Add Book");
        System.out.println("2. Search Book by Title");
        System.out.println("3. Update Book");
        System.out.println("4. Delete Book");
        System.out.println("5. Add Reservation");
        System.out.println("6. View Reservations by User");
        System.out.println("7. Cancel Reservation");
        System.out.println("8. Update Reservation Expiry Date");    
        System.out.print("Choose option: ");
        int option = sc.nextInt();
        sc.nextLine(); // consume newline

        if (option == 1) {
            System.out.print("Enter Book ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            String title = "";
            while (title.isBlank()) {
                System.out.print("Enter Title (required): ");
                title = sc.nextLine().trim();
            }

            String author = "";
            while (author.isBlank()) {
                System.out.print("Enter Author (required): ");
                author = sc.nextLine().trim();
            }

            System.out.print("Enter Publisher: ");
            String publisher = sc.nextLine();

            System.out.print("Enter Publication Year: ");
            int year = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Total Copies: ");
            int total = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Copies Available: ");
            int available = sc.nextInt();
            sc.nextLine();

            BookEntity book = new BookEntity(id, title, author, publisher, year, total, available);
            boolean success = dao.addBook(book);
            System.out.println(success ? "Book added." : "Failed to add book.");
        }

        else if (option == 2) {
            System.out.print("Enter search keyword: ");
            String keyword = sc.nextLine();

            List<BookEntity> results = dao.getBookByTitle(keyword);
            if (results.isEmpty()) {
                System.out.println("No books found.");
            } else {
                for (BookEntity book : results) {
                    System.out.println("ID: " + book.getBookId() +
                                       ", Title: " + book.getTitle() +
                                       ", Author: " + book.getAuthor() +
                                       ", Publisher: " + book.getPublisher() +
                                       ", Year: " + book.getPublicationYear() +
                                       ", Available: " + book.getCopiesAvailable() +
                                       "/" + book.getTotalCopies());
                }
            }
        }

        else if (option == 3) {
            System.out.print("Enter Book ID to Update: ");
            int id = sc.nextInt();
            sc.nextLine();
        
            System.out.print("Enter New Title: ");
            String title = sc.nextLine();
        
            System.out.print("Enter New Author: ");
            String author = sc.nextLine();
        
            System.out.print("Enter New Publisher: ");
            String publisher = sc.nextLine();
        
            System.out.print("Enter New Publication Year: ");
            int year = sc.nextInt();
            sc.nextLine();
        
            System.out.print("Enter New Total Copies: ");
            int total = sc.nextInt();
            sc.nextLine();
        
            System.out.print("Enter New Copies Available: ");
            int available = sc.nextInt();
            sc.nextLine();
        
            BookEntity book = new BookEntity(id, title, author, publisher, year, total, available);
            boolean updated = dao.updateBook(book);
            System.out.println(updated ? "Book updated." : "Failed to update book.");
        }

        else if (option == 4) {
            System.out.print("Enter Book ID to Delete: ");
            int id = sc.nextInt();
            sc.nextLine();
        
            boolean deleted = dao.deleteBook(id);
            System.out.println(deleted ? "Book deleted." : "Failed to delete book.");
        }

        else if (option == 5) {
            System.out.print("Enter User ID: ");
            int userId = sc.nextInt();
            sc.nextLine();
        
            System.out.print("Enter Book ID: ");
            int bookId = sc.nextInt();
            sc.nextLine();
        
            System.out.print("Enter Expiry Date (yyyy-MM-dd): ");
            String expiry = sc.nextLine();
            System.out.println("⏳ Got expiry as: " + expiry);  // <-- Debug print
            Timestamp expiresAt = Timestamp.valueOf(expiry + " 23:59:59");
            System.out.println("Converted to timestamp: " + expiresAt);  // <-- Debug print

        
            ReservationEntity res = new ReservationEntity(userId, bookId, expiresAt);
            boolean success = resDao.addReservation(res);
            System.out.println(success ? "Reservation added." : "Failed to add reservation.");
        }

        else if (option == 6) {
            System.out.print("Enter User ID to view reservations: ");
            int userId = sc.nextInt();
            sc.nextLine();
        
            List<ReservationEntity> reservations = resDao.getReservationsByUserId(userId);
            if (reservations.isEmpty()) {
                System.out.println("No reservations found.");
            } else {
                for (ReservationEntity res : reservations) {
                    System.out.println("Reservation ID: " + res.getReservationId() +
                                       ", Book ID: " + res.getBookId() +
                                       ", Reserved At: " + res.getReservedAt() +
                                       ", Expires At: " + res.getExpiresAt() +
                                       ", Status: " + res.getStatus());
                }
            }
        }

        else if (option == 7) {
            System.out.print("Enter Reservation ID to cancel: ");
            int reservationId = sc.nextInt();
            sc.nextLine();

            boolean cancelled = resDao.cancelReservation(reservationId);
            System.out.println(cancelled ? "Reservation cancelled." : "Failed to cancel reservation.");
        }

        else if (option == 8) {
            System.out.print("Enter Reservation ID to update expiry date: ");
            int reservationId = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter New Expiry Date (yyyy-MM-dd): ");
            String newExpiry = sc.nextLine();
            Timestamp newExpiresAt = Timestamp.valueOf(newExpiry + " 23:59:59");

            boolean updated = resDao.updateReservationExpiry(reservationId, newExpiresAt);
            System.out.println(updated ? "Reservation updated." : "Failed to update reservation.");
        }


        sc.close();
    }
}
